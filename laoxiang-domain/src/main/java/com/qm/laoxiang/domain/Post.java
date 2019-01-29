package com.qm.laoxiang.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter@Setter@ToString
public class Post {

    public Long id;
    public String title;
    public String content;
    public Integer yn;
    public Integer top;
    public Usr author;
    public Region authorRegion;
    public Region authorHometown;
    public Integer visitCnt;
    public Integer praiseCnt;
    public Integer replyCnt;
    public Date created;
    public Date modified;
}
