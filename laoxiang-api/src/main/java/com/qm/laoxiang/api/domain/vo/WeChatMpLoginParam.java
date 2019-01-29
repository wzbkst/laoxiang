package com.qm.laoxiang.api.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "微信用户登录参数")
public class WeChatMpLoginParam {
    /**
     * 微信小程序授权码
     */
    @ApiModelProperty(value = "微信授权码，使用授权码方式登录时必填，使用微信用户信息方式登陆时不填")
    private String authCode;

    /**
     * 接受邀请来注册时的邀请码
     */
    @ApiModelProperty(value = "注册邀请码，若用户是受邀来注册，填此参数，使用发邀请的用户的标识符填充，可以不填")
    private String inviteCode;

    /**
     * 微信getUserInfo返回的encryptedData，需使用前一步登陆逻辑中微信服务器返回的sessionKey进行解密
     */
    @ApiModelProperty(value = "微信getUserInfo返回的encryptedData，使用授权码方式登录时不填，使用微信用户信息方式登陆时必填")
    private String encryptedData;

    /**
     * 微信getUserInfo返回的iv
     */
    @ApiModelProperty(value = "微信getUserInfo返回的iv，使用授权码方式登录时不填，使用微信用户信息方式登陆时必填")
    private String iv;

    /**
     * 微信getUserInfo返回的signature
     */
    @ApiModelProperty(value = "微信getUserInfo返回的signature，使用授权码方式登录时不填，使用微信用户信息方式登陆时必填")
    private String signature;

    /**
     * 微信getUserInfo返回的rawData
     */
    @ApiModelProperty(value = "微信getUserInfo返回的rawData，使用授权码方式登录时不填，使用微信用户信息方式登陆时必填")
    private String rawData;

    /**
     * Aes加密后的微信用户的openId，用于获取缓存中的sessionKey
     */
    @ApiModelProperty(value = "使用授权码登陆时，若查不到用户的unionId，返回该字段作为用户标识，前端暂存下一步使用微信用户" +
            "信息方式登录时回传")
    private String uid;

    /**
     * 内部称呼
     */
    @ApiModelProperty(value = "内部称呼")
    private String name;
    /**
     * 所在地区id
     */
    @ApiModelProperty(value = "所在地区id")
    private Integer regionId;
    /**
     * 所在家乡id
     */
    @ApiModelProperty(value = "所在家乡id")
    private Integer hometownId;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;
}
