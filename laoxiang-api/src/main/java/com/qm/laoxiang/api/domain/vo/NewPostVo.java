package com.qm.laoxiang.api.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class NewPostVo {

    private String title;
    private String content;
    private Integer showDurtion;
    private String usrOpenId;
    private Integer top;
    private Integer regionId;
    private Integer hometownId;
}
