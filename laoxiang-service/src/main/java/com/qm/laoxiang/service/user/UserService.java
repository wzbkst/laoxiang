package com.qm.laoxiang.service.user;

import com.qm.laoxiang.common.exception.ServiceException;
import com.qm.laoxiang.domain.UserCreateRequest;
import com.qm.laoxiang.domain.UserInfoResult;
import com.qm.laoxiang.domain.Usr;

public interface UserService {

    UserInfoResult getUserInfo(String openId, String unionId) throws ServiceException;
    void createUser(UserCreateRequest userCreateRequest);
    Long getUserIdByOpenId(String openId);
    Usr getUserDetail(Long userId) throws ServiceException;
}
