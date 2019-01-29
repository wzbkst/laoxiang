package com.qm.laoxiang.api.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@ApiModel(value = "banner 图片信息")
public class BannerImageVo {

    @ApiModelProperty(value = "banner图片描述")
    public String desc;

    @ApiModelProperty(value = "跳转地址")
    public String srcUrl;

    @ApiModelProperty(value = "图片url地址")
    public String imageUrl;
}
