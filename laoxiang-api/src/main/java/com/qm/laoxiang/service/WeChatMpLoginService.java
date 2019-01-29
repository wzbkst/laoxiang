package com.qm.laoxiang.service;

import com.qm.laoxiang.api.domain.vo.WeChatMiniProgramUserVo;
import com.qm.laoxiang.api.domain.vo.WeChatMpLoginParam;
import com.qm.laoxiang.common.exception.ServiceException;

/**
 * 登陆分两种情况:
 * 1.使用授权码登陆，此时根据授权码可以获取到微信用户的unionId，对应方法为loginByAuthCode
 * 2.若1获取到微信用户的unionId，则采用第二种方式，前端使用微信小程序的加密用户数据（含unionId）来登陆
 * 对应方法loginByEncryptedUserInfo
 */
public interface WeChatMpLoginService {

    /**
     * 微信登录处理方法：
     * 1.调用微信接口获取用户会话信息
     * 2.缓存微信会话信息
     * 3.处理登录或者注册流程
     */
    WeChatMiniProgramUserVo loginByAuthCode(WeChatMpLoginParam userParam) throws ServiceException;

    /**
     * 处理用户信息解析流程，该方法在loginByAuthCode接口获取不到用户unionId的情况下才会调用，此时需要
     * 同时处理登陆逻辑
     * 从前端传入的微信加密数据中读取微信用户信息，含OpenId/unionId等敏感信息，步骤为：
     * 1.从参数的uid解析出openId
     * 2.从加密数据中解析出unionId
     * 3.根据unionId处理注册或者登陆，此处用openid代替unionId
     * @param userParam
     * @return
     * @throws ServiceException
     */
    WeChatMiniProgramUserVo loginByEncryptedUserInfo(WeChatMpLoginParam userParam) throws ServiceException;
}
