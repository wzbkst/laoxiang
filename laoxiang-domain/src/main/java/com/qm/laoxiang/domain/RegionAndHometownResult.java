package com.qm.laoxiang.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class RegionAndHometownResult {

    private Long userId;
    private Integer regionId;
    private String regionName;
    private Integer hometownId;
    private String hometownName;
}
