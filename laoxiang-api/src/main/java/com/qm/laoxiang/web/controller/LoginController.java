package com.qm.laoxiang.web.controller;

import com.qm.laoxiang.api.domain.vo.WeChatMiniProgramUserVo;
import com.qm.laoxiang.api.domain.vo.WeChatMpLoginParam;
import com.qm.laoxiang.common.exception.ServiceException;
import com.qm.laoxiang.common.web.domain.ResponseResult;
import com.qm.laoxiang.common.web.domain.ResultCode;
import com.qm.laoxiang.service.WeChatMpLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * login
 */
@Api(value="登陆接口")
@Controller
@RequestMapping("api/v1/login")
public class LoginController {
    private Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private WeChatMpLoginService weChatMpLoginService;


    @ApiOperation(value="授权码方式登陆", notes = "小程序获取微信授权码后调用此接口")
    @RequestMapping(value = "/wxmp/authcode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<WeChatMiniProgramUserVo> loginByAuthCode(@RequestBody WeChatMpLoginParam userParam) throws ServiceException {
        logger.info("接收微信用户使用授权码登陆，{}", userParam);
        if(StringUtils.isBlank(userParam.getAuthCode())){
            throw new ServiceException(ResultCode.ILLEGAL_PARAM.getCode(), "授权码不能为空");
        }
        WeChatMiniProgramUserVo vo = weChatMpLoginService.loginByAuthCode(userParam);
        ResponseResult<WeChatMiniProgramUserVo> rst = ResponseResult.success(vo);
        return rst;
    }

    @ApiOperation(value="使用用户信息方式登陆注册", notes = "前端授权后调用此接口")
    @RequestMapping(value = "/wxmp/userInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<WeChatMiniProgramUserVo> loginByEncryotedUserInfo(@RequestBody WeChatMpLoginParam userParam) throws ServiceException {
        logger.info("接收使用用户信息方式登陆，{}", userParam);
        if( ! checkParams(userParam)){
            throw new ServiceException(ResultCode.ILLEGAL_PARAM.getCode(), "参数错误");
        }
        WeChatMiniProgramUserVo vo = weChatMpLoginService.loginByEncryptedUserInfo(userParam);
        ResponseResult<WeChatMiniProgramUserVo> rst = ResponseResult.success(vo);
        return rst;
    }

    private boolean checkParams(WeChatMpLoginParam userParam){
        if(StringUtils.isBlank(userParam.getRawData()) || StringUtils.isBlank(userParam.getSignature())
                || StringUtils.isBlank(userParam.getIv()) || StringUtils.isBlank(userParam.getEncryptedData())){
            logger.info("参数错误，{}", userParam);
            return false;
        }
        return true;
    }
}
