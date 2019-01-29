package com.qm.laoxiang.service.post;

import com.qm.laoxiang.common.exception.ServiceException;
import com.qm.laoxiang.common.web.domain.query.QueryPostConditions;
import com.qm.laoxiang.domain.Post;
import com.qm.laoxiang.domain.db.PostInfo;
import com.qm.laoxiang.domain.utils.PageBean;

import java.util.List;

public interface PostService {

    PageBean<Post> queryPostsByPageAndConditions(int currentPage, int pageSize, QueryPostConditions queryPostConditions);
    Post queryPostDetailByConditions(QueryPostConditions queryPostConditions);
    int createPost(Post post) throws ServiceException;
}
