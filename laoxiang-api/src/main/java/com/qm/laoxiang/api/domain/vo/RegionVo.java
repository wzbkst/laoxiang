package com.qm.laoxiang.api.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter@Setter@ToString
@ApiModel(value = "用户所在地区信息")
public class RegionVo {

    @ApiModelProperty(value = "id")
    public String id;

    @ApiModelProperty(value = "名称")
    public String name;

}
