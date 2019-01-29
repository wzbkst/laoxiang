package com.qm.laoxiang.api.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class WeChatMiniProgramSessionInfo {

    @JSONField(name = "openId")
    private String openId;

    @JSONField(name = "session_key")
    private String sessionKey;

    @JSONField(name = "unionId")
    private String unionId;
}
