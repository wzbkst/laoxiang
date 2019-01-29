package com.qm.laoxiang.service.user.impl;

import com.qm.laoxiang.common.exception.ServiceException;
import com.qm.laoxiang.common.web.domain.ResultCode;
import com.qm.laoxiang.dao.mapper.HometownMapper;
import com.qm.laoxiang.dao.mapper.RegionMapper;
import com.qm.laoxiang.dao.mapper.UserMapper;
import com.qm.laoxiang.dao.mapper.UsrRegionHometownLinkMapper;
import com.qm.laoxiang.domain.Region;
import com.qm.laoxiang.domain.UserCreateRequest;
import com.qm.laoxiang.domain.UserInfoResult;
import com.qm.laoxiang.domain.Usr;
import com.qm.laoxiang.domain.db.*;
import com.qm.laoxiang.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UsrRegionHometownLinkMapper usrRegionHometownLinkMapper;

    @Autowired
    private HometownMapper hometownMapper;

    @Autowired
    private RegionMapper regionMapper;

    /**
     * unionId暂时无用
     * @param openId
     * @param unionId
     * @return
     */
    @Override
    public UserInfoResult getUserInfo(String openId, String unionId) throws ServiceException {
        UserExample query = new UserExample();
        UserExample.Criteria criteria = query.createCriteria();
        if(StringUtils.isNotBlank(openId)){
            criteria.andWxOpenidEqualTo(openId).andYnEqualTo(1);
        }
        query.setOrderByClause("created desc");
        List<User> usersInDb = userMapper.selectByExample(query);
        User userInDb = new User();
        if(CollectionUtils.isEmpty(usersInDb)){
            return null;
        }
        if(usersInDb.size() > 1){
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "用户记录有多条");
        }else{
            userInDb = usersInDb.get(0) ;
        }
        UserInfoResult result = convertToUserInfoResult(userInDb);
        UsrRegionHometownLinkExample usrRegionHometownLinkExample = new UsrRegionHometownLinkExample();
        usrRegionHometownLinkExample.createCriteria().andYnEqualTo(1).andUsrIdEqualTo(userInDb.getId());
        List<UsrRegionHometownLink> usrRegionHometownLinks = usrRegionHometownLinkMapper.selectByExample(usrRegionHometownLinkExample);
        UsrRegionHometownLink usrRegionHometownLink;
        if(CollectionUtils.isEmpty(usrRegionHometownLinks)){
            return null;
        }
        if(usrRegionHometownLinks.size() > 1){
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "用户家乡记录有多条");
        }else{
            usrRegionHometownLink = usrRegionHometownLinks.get(0) ;
        }
        result.setRegionId(usrRegionHometownLink.getRegionId());
        result.setHometownId(usrRegionHometownLink.getHometownId());
        logger.info("返回查询用户信息结果，结果={}", result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class, Exception.class})
    public void createUser(UserCreateRequest userCreateRequest) {
        User newUser = convertToUser(userCreateRequest);
        userMapper.insert(newUser);
        UsrRegionHometownLink userLink = new UsrRegionHometownLink();
        Date now = new Date();
        userLink.setHometownId(userCreateRequest.getHometownId());
        userLink.setRegionId(userCreateRequest.getRegionId());
        userLink.setCreated(now);
        userLink.setModified(now);
        userLink.setYn(1);
        userLink.setUsrId(newUser.getId());
        usrRegionHometownLinkMapper.insert(userLink);
    }

    @Override
    public Long getUserIdByOpenId(String openId) {
        UserExample query = new UserExample();
        query.createCriteria().andYnEqualTo(1).andWxOpenidEqualTo(openId);
        List<User> usersInDb = userMapper.selectByExample(query);
        if(CollectionUtils.isEmpty(usersInDb)){
            return null;
        }
        if(usersInDb.size() > 1){
            return null;
        }else{
            return usersInDb.get(0).getId();
        }
    }

    @Override
    public Usr getUserDetail(Long userId) throws ServiceException {
        User usersInDb = userMapper.selectByPrimaryKey(userId);
        Usr usr = new Usr();
        usr.setOpenId(usersInDb.getWxOpenid());
        usr.setWxAvatar(usersInDb.getWxAvatar());
        usr.setWxGender(usersInDb.getWxGender());
        usr.setNickname(usersInDb.getNickname());
        UsrRegionHometownLinkExample usrRegionHometownLinkExample = new UsrRegionHometownLinkExample();
        usrRegionHometownLinkExample.createCriteria().andYnEqualTo(1).andUsrIdEqualTo(usersInDb.getId());
        List<UsrRegionHometownLink> usrRegionHometownLinks = usrRegionHometownLinkMapper.selectByExample(usrRegionHometownLinkExample);
        UsrRegionHometownLink usrRegionHometownLink;
        if(CollectionUtils.isEmpty(usrRegionHometownLinks)){
            return null;
        }
        if(usrRegionHometownLinks.size() > 1){
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "用户家乡记录有多条");
        }else{
            usrRegionHometownLink = usrRegionHometownLinks.get(0) ;
        }
        Hometown hometownInDb = hometownMapper.selectByPrimaryKey(usrRegionHometownLink.getHometownId());
        com.qm.laoxiang.domain.db.Region regionInDb = regionMapper.selectByPrimaryKey(usrRegionHometownLink.getHometownId());
        Region region = new Region();
        region.setId(regionInDb.getId());
        region.setName(regionInDb.getName());
        usr.setRegion(region);
        Region hometown = new Region();
        hometown.setId(hometownInDb.getId());
        hometown.setName(hometownInDb.getName());
        usr.setRegion(hometown);
        return usr;
    }

    private User convertToUser(UserCreateRequest userCreateRequest){
        User user = new User();
        user.setNickname(userCreateRequest.getName());
        user.setWxOpenid(userCreateRequest.getOpenId());
        user.setWxNickname(userCreateRequest.getNickName());
        user.setWxAvatar(userCreateRequest.getAvatarUrl());
        user.setWxAge(userCreateRequest.getAge());
        user.setWxGender(userCreateRequest.getGender());
        user.setWxCountry(userCreateRequest.getCountry());
        user.setWxProvince(userCreateRequest.getProvince());
        user.setWxCity(userCreateRequest.getCity());
        user.setYn(1);
        Date now = new Date();
        user.setCreated(now);
        user.setModified(now);
        return user;
    }

    private UserInfoResult convertToUserInfoResult(User userInDb) {
        if(userInDb == null){
            return null;
        }
        UserInfoResult userInfoResult = new UserInfoResult();
        userInfoResult.setId(userInDb.getId());
        userInfoResult.setOpenId(userInDb.getWxOpenid());
        userInfoResult.setNickName(userInDb.getWxNickname());
        userInfoResult.setStatus(userInDb.getYn());
        userInfoResult.setAvatar(userInDb.getWxAvatar());
        userInfoResult.setGender(userInDb.getWxGender());
        userInfoResult.setCountry(userInDb.getWxCountry());
        userInfoResult.setProvince(userInDb.getWxProvince());
        userInfoResult.setCity(userInDb.getWxCity());
        userInfoResult.setName(userInDb.getNickname());
        return userInfoResult;
    }
}
