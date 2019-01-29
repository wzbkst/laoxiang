package com.qm.laoxiang.common.web.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@ApiModel(value = "查询帖子条件")
public class QueryPostConditions {
    /**
     * 所在地区id
     */
    @ApiModelProperty(value = "所在地区id", notes = "所在地区id")
    private Integer regionId;
    /**
     * 家乡id
     */
    @ApiModelProperty(value = "家乡id", notes = "家乡id")
    private Integer hometownId;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "作者id", notes = "作者id")
    private Long authorId;
    /**
     * 帖子id
     */
    @ApiModelProperty(value = "帖子id", notes = "帖子id")
    private Long postId;
}
