package com.qm.laoxiang.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Getter@Setter@ToString
public abstract class BaseRequest implements Serializable{
    private String opName;
    private String opIp;
    private String uuid = UUID.randomUUID().toString();
}
