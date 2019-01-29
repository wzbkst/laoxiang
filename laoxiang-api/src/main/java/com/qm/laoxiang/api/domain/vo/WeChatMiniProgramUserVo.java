package com.qm.laoxiang.api.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@NoArgsConstructor
@ApiModel(value = "登陆结果")
public class WeChatMiniProgramUserVo {
    /**
     * 老用户
     */
    public static final int USER_FLAG_OLD = 0;

    /**
     * 新用户
     */
    public static final int USER_FLAG_NEW = 1;

    /**
     * 需要用户授权
     */
    public static final int AUTH_FLAG_NEED = 1;

    /**
     * 无需用户授权
     */
    public static final int AUTH_FLAG_NOT_NEED = 0;

    /**
     * 新老用户标识,0-新用户，1-老用户
     */
    @ApiModelProperty(value = "新老用户标识,0-新用户，1-老用户")
    private int userFlag;

    /**
     * 登录后的会话标识key, jwt token
     */
    @ApiModelProperty(name = "会话标识", value = "会话标识，若根据授权码能拿到unionId，则返回值，否则为空，前端引导用户授权，"+
    "token采用jwt规范，以英文.分割为3段，每一段都是base64编码，第二段解析后获取subject，为次用户标识")
    private String token;

    /**
     * 若用户使用微信授权码登录获取不到unionId时返回的用户标识，前端保存此值，待调用getUserInfo后回传，
     * 此时需包含使用对称加密算法的openId返回给前端，次加密后的数据设置到uid
     */
    @ApiModelProperty(value = "用户使用微信授权码登录，获取不到unionId时返回的用户标识，前端保存此值，待调用getUserInfo后回传")
    private String uid;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 性别，0-未知，1-男 ，2-女
     */
    @ApiModelProperty(value = "性别，0-未知，1-男 ，2-女")
    private Integer gender;

    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    private String city;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String province;

    /**
     * 国家
     */
    @ApiModelProperty(value = "国家")
    private String country;

    /**
     * 微信头像url
     */
    @ApiModelProperty(value = "微信头像url")
    private String avatar;

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
