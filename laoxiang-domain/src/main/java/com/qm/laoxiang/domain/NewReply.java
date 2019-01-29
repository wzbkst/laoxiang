package com.qm.laoxiang.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter@Setter@ToString
public class NewReply {

    public String openId;
    public Long replyId;
    public Long postId;
    public String content;
    private Integer regionId;
    private Integer hometownId;
}
