package com.qm.laoxiang.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter@Setter@ToString
public class Usr {

    public String id;
    public String nickname;
    public String realname;
    public String openId;
    public Region region;
    public Region hometown;
    public String wxAvatar;
    public Integer wxNickname;
    public Integer age;
    public Integer wxGender;
    public Date created;
    public String yn;
}
