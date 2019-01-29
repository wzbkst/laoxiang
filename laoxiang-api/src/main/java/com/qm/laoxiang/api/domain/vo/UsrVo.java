package com.qm.laoxiang.api.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter@Setter@ToString
@ApiModel(value = "用户信息")
public class UsrVo {

    @ApiModelProperty(value = "用户id")
    public String id;

    @ApiModelProperty(value = "用户昵称")
    public String nickname;

    @ApiModelProperty(value = "用户真实姓名")
    public String realname;

    @ApiModelProperty(value = "用户openId")
    public String openId;

    @ApiModelProperty(value = "用户所在地")
    public RegionVo region;

    @ApiModelProperty(value = "用户家乡")
    public RegionVo hometown;

    @ApiModelProperty(value = "用户微信头像地址")
    public String wxAvatar;

    @ApiModelProperty(value = "用户微信昵称")
    public Integer wxNickname;

    @ApiModelProperty(value = "用户年龄")
    public Integer age;

    @ApiModelProperty(value = "用户性别")
    public Integer wxGender;

    @ApiModelProperty(value = "用户注册时间")
    public Date created;

    @ApiModelProperty(value = "用户状态")
    public String yn;
}
