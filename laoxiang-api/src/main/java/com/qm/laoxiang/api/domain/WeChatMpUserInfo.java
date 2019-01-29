package com.qm.laoxiang.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString@NoArgsConstructor
public class WeChatMpUserInfo {
    private String openId;
    private String nickName;
    private Integer gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
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

    public WeChatMpUserInfo(String openId, String unionId, String nickName) {
        this.openId = openId;
        this.nickName = nickName;
        this.unionId = unionId;
    }
}
