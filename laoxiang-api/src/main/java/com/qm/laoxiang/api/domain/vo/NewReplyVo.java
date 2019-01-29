package com.qm.laoxiang.api.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter@Setter@ToString
@ApiModel(value = "回复帖子")
public class NewReplyVo {

//    @ApiModelProperty(value = "回复者的openid")  //改为从登陆里根据openId获取id
//    public String openId;

    @ApiModelProperty(value = "回复的回复id")
    public Long replyId;

    @ApiModelProperty(value = "回复的帖子id")
    public Long postId;

    @ApiModelProperty(value = "回复的内容")
    public String content;

    @ApiModelProperty(value = "回复时作者openid")
    public String openId;
    private Integer regionId;
    private Integer hometownId;


}
