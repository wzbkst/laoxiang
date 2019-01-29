package com.qm.laoxiang.service.reply;

import com.qm.laoxiang.common.exception.ServiceException;
import com.qm.laoxiang.common.web.domain.query.QueryReplyConditions;
import com.qm.laoxiang.domain.NewReply;
import com.qm.laoxiang.domain.Reply;
import com.qm.laoxiang.domain.utils.PageBean;

public interface ReplyService {

    PageBean<Reply> queryReplyByPageAndConditions(int currentPage, int pageSize, QueryReplyConditions queryReplyConditions);
    int createReply(NewReply newReply) throws ServiceException;
}
