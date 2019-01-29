package com.qm.laoxiang.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class UserCreateRequest extends BaseRequest{
    private String unionId;
    private String openId;
    private String inviteCode;
    private String nickName;
    private Integer gender;
    private String age;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    /**
     * 内部称呼
     */
    private String name;
    /**
     * 所在地区id
     */
    private Integer regionId;
    /**
     * 所在家乡id
     */
    private Integer hometownId;
    /**
     * 性别
     */
    private Integer sex;
}
