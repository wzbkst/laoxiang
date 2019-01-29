package com.qm.laoxiang.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter@Setter@ToString
public class UserInfoResult {

    private Long id;
    private String unionId;
    private String openId;
    /**
     * 0-无效，1-有效
     */
    private Integer status;
    private String nickName;
    private Integer gender;
    private String city;
    private String province;
    private String country;
    private String avatar;
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
