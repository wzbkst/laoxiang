package com.qm.laoxiang.api.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter@Setter@ToString
@ApiModel(value = "帖子信息")
public class PostInfoVo {

    @ApiModelProperty(value = "帖子id")
    public Long id;

    @ApiModelProperty(value = "帖子标题")
    public String title;

    @ApiModelProperty(value = "帖子内容")
    public String content;

    @ApiModelProperty(value = "帖子状态")
    public Integer yn;

    @ApiModelProperty(value = "帖子是否置顶")
    public Integer top;

    @ApiModelProperty(value = "帖子创建者信息")
    public UsrVo author;

    @ApiModelProperty(value = "发帖时作者所在地区")
    public RegionVo authorRegion;

    @ApiModelProperty(value = "发帖时作者家乡")
    public RegionVo authorHometown;

    @ApiModelProperty(value = "帖子访问量")
    public Integer visitCnt;

    @ApiModelProperty(value = "帖子点赞数")
    public Integer praiseCnt;

    @ApiModelProperty(value = "帖子回复数")
    public Integer replyCnt;

    @ApiModelProperty(value = "帖子创建时间")
    public Date created;

    @ApiModelProperty(value = "帖子修改时间")
    public Date modified;
}
