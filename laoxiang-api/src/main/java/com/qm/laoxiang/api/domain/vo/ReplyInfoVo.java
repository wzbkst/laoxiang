package com.qm.laoxiang.api.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter@Setter@ToString
@ApiModel(value = "帖子回复信息")
public class ReplyInfoVo {

    @ApiModelProperty(value = "回复id")
    public Long id;

    @ApiModelProperty(value = "帖子id")
    public Long postId;

    @ApiModelProperty(value = "回复的内容")
    public String content;

    @ApiModelProperty(value = "回复作者信息")
    public UsrVo author;

    @ApiModelProperty(value = "回复是否置顶")
    public Integer top;

    @ApiModelProperty(value = "回复时作者所在地区")
    public RegionVo authorRegion;

    @ApiModelProperty(value = "回复时作者家乡")
    public RegionVo authorHometown;

    @ApiModelProperty(value = "回复点赞数")
    public Integer praiseCnt;

    @ApiModelProperty(value = "回复发表时间")
    public Date created;

    @ApiModelProperty(value = "回复修改时间")
    public Date modified;
}
