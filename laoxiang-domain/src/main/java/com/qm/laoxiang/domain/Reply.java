package com.qm.laoxiang.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter@Setter@ToString
public class Reply {

    public Long id;
    public String content;
    public Long replyId;
    public Long replyParentId;
    public Long postId;
    public Integer yn;
    public String top;
    public Usr author;
    public Region authorRegion;
    public Region authorHometown;
    public Integer praiseCnt;
    public Date created;
    public Date modified;
}
