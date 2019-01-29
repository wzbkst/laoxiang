package com.qm.laoxiang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qm.laoxiang.api.domain.WeChatMiniProgramSessionInfo;
import com.qm.laoxiang.api.domain.WeChatMpUserInfo;
import com.qm.laoxiang.api.domain.vo.WeChatMiniProgramUserVo;
import com.qm.laoxiang.api.domain.vo.WeChatMpLoginParam;
import com.qm.laoxiang.cache.util.RedisUtil;
import com.qm.laoxiang.common.exception.ServiceException;
import com.qm.laoxiang.common.utils.AesPadding7Utils;
import com.qm.laoxiang.common.utils.AesUtil;
import com.qm.laoxiang.common.web.domain.ResultCode;
import com.qm.laoxiang.domain.UserCreateRequest;
import com.qm.laoxiang.domain.UserInfoResult;
import com.qm.laoxiang.service.WeChatMpLoginService;
import com.qm.laoxiang.service.user.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Consts;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class WeChatMpLoginServiceImpl implements WeChatMpLoginService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${wechat.mini.program.session.url}")
    private String sessionUrl;

    @Value("${wechat.mini.program.appId}")
    private String wechatMiniProgramAppId;

    @Value("${wechat.mini.program.secret}")
    private String wechatMiniProgramSecret;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public WeChatMiniProgramUserVo loginByAuthCode(WeChatMpLoginParam userParam) throws ServiceException{
        checkSessionParam(userParam);
        WeChatMiniProgramSessionInfo userSessionInfo = requestWeChatMpSessionInfo(userParam);
        //会话信息保存到缓存，存活时长3分钟
        redisUtil.set("user:"+userSessionInfo.getOpenId(), userSessionInfo.getSessionKey(),1800);
        WeChatMpUserInfo weChatMpUserInfo = new WeChatMpUserInfo(userSessionInfo.getOpenId(), userSessionInfo.getUnionId(), null);
        WeChatMiniProgramUserVo rst = handleLogin(userParam.getInviteCode(), weChatMpUserInfo);
        return rst;
    }

    @Override
    public WeChatMiniProgramUserVo loginByEncryptedUserInfo(WeChatMpLoginParam userParam) throws ServiceException {
//        String openId = AesUtil.decrypt(AesUtil.KEY_RULE, userParam.getUid());
        String openId = userParam.getUid();
        WeChatMpUserInfo weChatMpUserInfo = parseEncryptedData(userParam, openId);
        WeChatMiniProgramUserVo vo = handleLogin(userParam.getInviteCode(), weChatMpUserInfo);
        return vo;
    }

    private WeChatMpUserInfo parseEncryptedData(WeChatMpLoginParam userParam, String openId) throws ServiceException {
        String wxSessionKey= (String)redisUtil.get("user:"+openId);
        if(StringUtils.isBlank(wxSessionKey)){
            logger.error("根据openId获取不到该用户的微信会话信息，uid={}，openId={}", userParam.getUid(), openId);
            throw new ServiceException(ResultCode.ILLEGAL_PARAM.getCode(), "没有用户的会话信息");
        }
        String signatureNew = DigestUtils.sha1Hex(userParam.getRawData() + wxSessionKey);
        if(StringUtils.isBlank(signatureNew)){
            logger.error("加密数据签名校验错误，uid={}，openId={}", userParam.getUid(), openId);
            throw new ServiceException(ResultCode.ILLEGAL_PARAM.getCode(), "加密数据签名校验错误");
        }
        String decryptedData = AesPadding7Utils.decrypt(userParam.getEncryptedData(), wxSessionKey, userParam.getIv());
        JSONObject jsonObject = JSON.parseObject(decryptedData);
        String nickName = (String) jsonObject.get("nickName");
        String avatarUrl = (String) jsonObject.get("avatarUrl");
        String country = (String) jsonObject.get("country");
        String province = (String) jsonObject.get("province");
        String city = (String) jsonObject.get("city");
        Integer gender = (Integer) jsonObject.get("gender");
        String openIdInEncryptedData = (String) jsonObject.get("openId");
//        Object unionIdObj = jsonObject.get("unionId");
//        if(unionIdObj == null){
//            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "加密信息中不含有unionId");
//        }
//        String unionId = (String) unionIdObj;
        if( ! StringUtils.equals(openId, openIdInEncryptedData)){
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "openId不匹配");
        }
        Map watermark = (Map)jsonObject.get("watermark");
        Object appIdObj = watermark.get("appid");
        if(appIdObj != null ){
            if(! StringUtils.equals(wechatMiniProgramAppId, (String) appIdObj)){
                throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "微信appId不符");
            }
        }else{
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "加密数据中无appid");
        }
        WeChatMpUserInfo weChatMpUserInfo= new WeChatMpUserInfo(openId, "", nickName);
        weChatMpUserInfo.setAvatarUrl(avatarUrl);
        weChatMpUserInfo.setCountry(country);
        weChatMpUserInfo.setProvince(province);
        weChatMpUserInfo.setCity(city);
        weChatMpUserInfo.setGender(gender);
        weChatMpUserInfo.setName(userParam.getName());
        weChatMpUserInfo.setRegionId(userParam.getRegionId());
        weChatMpUserInfo.setHometownId(userParam.getHometownId());
        weChatMpUserInfo.setSex(userParam.getSex());
        logger.info("微信解析用户为；{}", weChatMpUserInfo);
        return weChatMpUserInfo;
    }

    private String getSession(String openId) {
        return openId;
    }

    /**
     * 处理用户登陆 或者注册的流程
     * 1.根据微信用户会话信息查询用户是否是新用户
     * 2.若是新用户注册，记录数据
     * 3.若是老用户，则返回记录
     * @param inviteCode
     * @param weChatMpUserInfo
     * @return
     */
    private WeChatMiniProgramUserVo handleLogin(String inviteCode, WeChatMpUserInfo weChatMpUserInfo) throws ServiceException {
        UserInfoResult userInfoResult = getUserInfo(weChatMpUserInfo.getOpenId(), weChatMpUserInfo.getUnionId());
        WeChatMiniProgramUserVo vo = new WeChatMiniProgramUserVo();
        if(userInfoResult == null){ //新用户
            vo = handleNewUser(inviteCode, weChatMpUserInfo);
        }else{
            vo = handleOldUser(userInfoResult);
        }
        return vo;
    }

    private UserInfoResult getUserInfo(String openId, String unionId) throws ServiceException {
        return userService.getUserInfo(openId, unionId);
    }

    private WeChatMiniProgramUserVo handleOldUser(UserInfoResult userInfoResult) {
        WeChatMiniProgramUserVo vo = new WeChatMiniProgramUserVo();
        BeanUtils.copyProperties(userInfoResult, vo);
        vo.setUserFlag(WeChatMiniProgramUserVo.USER_FLAG_OLD);
//        String token = generateToken(userInfoResult.getUnionId());
        String token = userInfoResult.getOpenId();
        vo.setToken(token);
        vo.setAvatar(userInfoResult.getAvatar());
        vo.setNickName(userInfoResult.getNickName());
        vo.setGender(userInfoResult.getGender());
        vo.setCountry(userInfoResult.getCountry());
        vo.setProvince(userInfoResult.getProvince());
        vo.setCity(userInfoResult.getCity());
        return vo;
    }

    private WeChatMiniProgramUserVo handleNewUser(String inviteCode, WeChatMpUserInfo weChatMpUserInfo) {
        WeChatMiniProgramUserVo vo = new WeChatMiniProgramUserVo();
        //设置标识符让前端引导用户授权
        vo.setUserFlag(WeChatMiniProgramUserVo.USER_FLAG_NEW);
//        String uid = AesUtil.encrypt();
        String uid = weChatMpUserInfo.getOpenId();
        vo.setUid(weChatMpUserInfo.getOpenId());
        logger.info("uid is {}", uid);
        //新用户，nickName等不为空才创建账号
        if(StringUtils.isNotBlank(weChatMpUserInfo.getNickName())){
//            String token = generateToken(weChatMpUserInfo.getUnionId());
            String token = weChatMpUserInfo.getOpenId();
            vo.setToken(token);
            UserCreateRequest userCreateRequest = new UserCreateRequest();
            userCreateRequest.setUnionId(weChatMpUserInfo.getUnionId());
            userCreateRequest.setOpenId(weChatMpUserInfo.getOpenId());
            userCreateRequest.setInviteCode(inviteCode);
            userCreateRequest.setNickName(weChatMpUserInfo.getNickName());
            userCreateRequest.setAvatarUrl(weChatMpUserInfo.getAvatarUrl());
            userCreateRequest.setGender(weChatMpUserInfo.getGender());
            userCreateRequest.setCountry(weChatMpUserInfo.getCountry());
            userCreateRequest.setProvince(weChatMpUserInfo.getProvince());
            userCreateRequest.setCity(weChatMpUserInfo.getCity());
            userCreateRequest.setName(weChatMpUserInfo.getName());
            userCreateRequest.setRegionId(weChatMpUserInfo.getRegionId());
            userCreateRequest.setHometownId(weChatMpUserInfo.getHometownId());
            userCreateRequest.setSex(weChatMpUserInfo.getSex());
            userService.createUser(userCreateRequest);
        }
        return vo;
    }

    /**
     * 根据用户的unionId生成会话令牌
     * 先对unionId做AES对称加密，然后用这个作为用户标识生成token
     * @param unionId
     * @return
     */
    private String generateToken(String unionId) {
        return "";
    }

    /**
     * 请求微信接口，获取用户会话信息
     * @param userParam
     * @return
     */
    private WeChatMiniProgramSessionInfo requestWeChatMpSessionInfo(WeChatMpLoginParam userParam) throws ServiceException {
        String url = sessionUrl.replace("APPID", wechatMiniProgramAppId)
                .replace("SECRET", wechatMiniProgramSecret)
                .replace("JSCODE", userParam.getAuthCode());
        logger.info("开始请求微信小程序会话接口，authCode：{}", userParam.getAuthCode());
        Request request = Request.Get(url).connectTimeout(1000).socketTimeout(3000);
        String responseString;
        try{
            responseString = Executor.newInstance().execute(request).returnContent().asString(Consts.UTF_8);
            logger.info("请求微信小程序会话接口->结束，responseString: {}", responseString);
        }catch (IOException e){
            logger.error("请求微信小程序接口获取sessionKey异常，authcode{}", userParam.getAuthCode());
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "获取微信小程序登陆信息失败");
        }
        if(StringUtils.isBlank(responseString)){
            logger.error("请求微信小程序接口响应为空，authcode{}", userParam.getAuthCode());
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "获取微信小程序登陆信息为空");
        }
        WeChatMiniProgramSessionInfo userSessionInfo = JSON.parseObject(responseString, WeChatMiniProgramSessionInfo.class);
        if(userSessionInfo == null){
            logger.error("请求微信小程序接口响应转化失败，authcode{}", userParam.getAuthCode());
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "获取微信小程序登陆信息转化失败");
        }
        if(StringUtils.isBlank(userSessionInfo.getSessionKey())){
            logger.error("请求微信小程序接口响应缺失sessionKey，authcode{}", userParam.getAuthCode());
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "获取微信小程序登陆信息转化失败");
        }
        if(StringUtils.isBlank(userSessionInfo.getOpenId())){
            logger.error("请求微信小程序接口响应缺失OpenId，authcode{}", userParam.getAuthCode());
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "获取微信小程序登陆信息转化失败");
        }
        return userSessionInfo;
    }

    private void checkSessionParam(WeChatMpLoginParam userParam) throws ServiceException {
        if(userParam == null){
            throw new ServiceException(ResultCode.ILLEGAL_PARAM.getCode(), "用户参数不能为空");
        }
        if(StringUtils.isBlank(userParam.getAuthCode())){
            throw new ServiceException(ResultCode.ILLEGAL_PARAM.getCode(), "授权码不能为空");
        }
    }
}
