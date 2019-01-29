package com.qm.laoxiang.service.post.impl;

import com.github.pagehelper.PageHelper;
import com.qm.laoxiang.common.exception.ServiceException;
import com.qm.laoxiang.common.web.domain.ResultCode;
import com.qm.laoxiang.common.web.domain.query.QueryPostConditions;
import com.qm.laoxiang.dao.mapper.HometownMapper;
import com.qm.laoxiang.dao.mapper.PostInfoMapper;
import com.qm.laoxiang.dao.mapper.RegionMapper;
import com.qm.laoxiang.dao.mapper.UserMapper;
import com.qm.laoxiang.domain.Post;
import com.qm.laoxiang.domain.Region;
import com.qm.laoxiang.domain.Usr;
import com.qm.laoxiang.domain.db.Hometown;
import com.qm.laoxiang.domain.db.PostInfo;
import com.qm.laoxiang.domain.db.PostInfoExample;
import com.qm.laoxiang.domain.utils.PageBean;
import com.qm.laoxiang.service.post.PostService;
import com.qm.laoxiang.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostInfoMapper postInfoMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private HometownMapper hometownMapper;
    @Autowired
    private RegionMapper regionMapper;
    @Override
    public PageBean<Post> queryPostsByPageAndConditions(int currentPage, int pageSize, QueryPostConditions queryPostConditions) {
        PostInfoExample query = new PostInfoExample();
        query.createCriteria().andYnEqualTo(1);
        if(queryPostConditions.getAuthorId()!=null){
            query.getOredCriteria().get(0).andUsrIdEqualTo(queryPostConditions.getAuthorId());
        }
        if(queryPostConditions.getHometownId()!= null && queryPostConditions.getHometownId() > 0){
            query.getOredCriteria().get(0).andHometownIdEqualTo(queryPostConditions.getHometownId());
        }
        if(queryPostConditions.getRegionId()!= null && queryPostConditions.getRegionId() > 0){
            query.getOredCriteria().get(0).andRegionIdEqualTo(queryPostConditions.getRegionId());
        }
        query.setOrderByClause("created desc");
        //设置分页信息
        PageHelper.startPage(currentPage, pageSize);
        List<PostInfo> allPostsInDB = postInfoMapper.selectByExample(query);
        long countNums = postInfoMapper.countByExample(query);            //总记录数
        PageBean<Post> pageData = new PageBean(currentPage, pageSize, countNums);
        List<Post> allPosts = new ArrayList<>();
        if( ! CollectionUtils.isEmpty(allPostsInDB)){
            allPosts = allPostsInDB.stream().map(postInDB -> {
                Post post = new Post();
                BeanUtils.copyProperties(postInDB, post);
                try {
                    Usr author = userService.getUserDetail(postInDB.getUsrId());
                    post.setAuthor(author);
                    Hometown hometownInDb = hometownMapper.selectByPrimaryKey(postInDB.getHometownId());
                    Region hometown = new Region();
                    hometown.setId(postInDB.getHometownId());
                    hometown.setName(hometownInDb.getName());
                    post.setAuthorHometown(hometown);
                    com.qm.laoxiang.domain.db.Region regionInDb = regionMapper.selectByPrimaryKey(postInDB.getRegionId());
                    Region region = new Region();
                    region.setId(postInDB.getRegionId());
                    region.setName(regionInDb.getName());
                    post.setAuthorRegion(region);
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return null;
                }
                return post;
            }).collect(Collectors.toList());
        }
        pageData.setItems(allPosts);
        return pageData;
    }

    @Override
    public Post queryPostDetailByConditions(QueryPostConditions queryPostConditions) {
        PostInfoExample query = new PostInfoExample();
        query.createCriteria().andYnEqualTo(1);
        if(queryPostConditions.getPostId()!= null && queryPostConditions.getPostId()> 0){
            query.getOredCriteria().get(0).andIdEqualTo(queryPostConditions.getPostId().longValue());
        }
        query.setOrderByClause("created desc");
        List<PostInfo> allPosts = postInfoMapper.selectByExample(query);
        if(CollectionUtils.isEmpty(allPosts)){
            return null;
        }else{
            Post post = new Post();
            BeanUtils.copyProperties(allPosts.get(0), post);
            PostInfo postInDB = allPosts.get(0);
            try {
                Usr author = userService.getUserDetail(postInDB.getUsrId());
                post.setAuthor(author);
                Hometown hometownInDb = hometownMapper.selectByPrimaryKey(postInDB.getHometownId());
                Region hometown = new Region();
                hometown.setId(postInDB.getHometownId());
                hometown.setName(hometownInDb.getName());
                post.setAuthorHometown(hometown);
                com.qm.laoxiang.domain.db.Region regionInDb = regionMapper.selectByPrimaryKey(postInDB.getRegionId());
                Region region = new Region();
                region.setId(postInDB.getRegionId());
                region.setName(regionInDb.getName());
                post.setAuthorRegion(region);
            } catch (ServiceException e) {
                e.printStackTrace();
                return null;
            }
            return post;
        }
    }

    @Override
    public int createPost(Post post) throws ServiceException {
        PostInfo postInfo = new PostInfo();
        Date now = new Date();
        postInfo.setCreated(now);
        postInfo.setModified(now);
        postInfo.setYn(1);
        postInfo.setPraiseCnt(0);
        postInfo.setVisitCnt(0);
        postInfo.setTitle(post.getTitle());
        postInfo.setContent(post.getContent());
        postInfo.setHometownId(post.getAuthorHometown().getId());
        postInfo.setRegionId(post.getAuthorRegion().getId());
        Long userId = userService.getUserIdByOpenId(post.getAuthor().getOpenId());
        if(userId == null){
            throw new ServiceException(ResultCode.ILLEGAL_PARAM.getCode(), "不存在此用户");
        }
        postInfo.setUsrId(userId);
        return postInfoMapper.insert(postInfo);
    }
}
