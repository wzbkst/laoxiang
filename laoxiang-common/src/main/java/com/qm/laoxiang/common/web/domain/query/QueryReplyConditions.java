package com.qm.laoxiang.common.web.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@ApiModel(value = "查询帖子回复条件")
public class QueryReplyConditions {
    /**
     * 所在地区id
     */
    @ApiModelProperty(value = "所在地区id", notes = "所在地区id")
    private Long regionId;
    /**
     * 家乡id
     */
    @ApiModelProperty(value = "家乡id", notes = "家乡id")
    private Long hometownId;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "作者id", notes = "作者id")
    private String authorId;
    /**
     * 帖子id
     */
    @ApiModelProperty(value = "帖子id", notes = "帖子id")
    private Integer postId;
}
