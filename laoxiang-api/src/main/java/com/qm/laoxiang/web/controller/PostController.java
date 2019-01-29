package com.qm.laoxiang.web.controller;

import com.alibaba.fastjson.JSON;
import com.qm.laoxiang.api.domain.vo.NewPostVo;
import com.qm.laoxiang.api.domain.vo.PostInfoVo;
import com.qm.laoxiang.common.web.domain.ResponseResult;
import com.qm.laoxiang.common.web.domain.ResultCode;
import com.qm.laoxiang.domain.Post;
import com.qm.laoxiang.domain.Region;
import com.qm.laoxiang.domain.Usr;
import com.qm.laoxiang.domain.db.PostInfo;
import com.qm.laoxiang.common.web.domain.query.QueryPostConditions;
import com.qm.laoxiang.domain.utils.PageBean;
import com.qm.laoxiang.service.post.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

/**
 * hello
 */
@Api(value="帖子相关接口")
@Controller
@RequestMapping("api/v1/post")
public class PostController {
    private Logger logger= LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @ApiOperation(value="查询帖子接口")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<PageBean<PostInfoVo>> getPosts(int currentPage, int pageSize, QueryPostConditions queryPostConditions){
        logger.info("in PostController->getPosts, get request:");
        ResponseResult<PageBean<PostInfoVo>> responseResult = new ResponseResult().setCode(ResultCode.SUCCESS.getCode());
        PageBean<PostInfoVo> results = new PageBean<>();
        try{
            PageBean<Post> postResults = postService.queryPostsByPageAndConditions(currentPage, pageSize, queryPostConditions);
            BeanUtils.copyProperties(postResults, results);
            if(CollectionUtils.isNotEmpty(postResults.getItems())){
                results.setItems(postResults.getItems().stream().map(post -> {
                    PostInfoVo postInfoVo = JSON.parseObject(JSON.toJSONString(post), PostInfoVo.class);
                    if(postInfoVo.getTitle().length() > 100){
                        postInfoVo.setTitle(postInfoVo.getTitle().substring(0, 100) + "...");
                    }
                    if(postInfoVo.getContent().length() > 100){
                        postInfoVo.setContent(postInfoVo.getContent().substring(0,100)+"...");
                    }
                    return postInfoVo;
                }).collect(Collectors.toList()));
            }
            responseResult.setData(results);
        }catch (Exception e){
            responseResult.setCode(ResultCode.INTERNAL_ERR.getCode());
            e.printStackTrace();
        }
        return responseResult;
    }

    @ApiOperation(value="查询帖子详情接口")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<PostInfoVo> queryPost(QueryPostConditions queryPostConditions){
        logger.info("in PostController->queryPost, get request:");
        ResponseResult<PostInfoVo> responseResult = new ResponseResult().setCode(ResultCode.SUCCESS.getCode());
        PostInfoVo results = null;
        try{
            Post postResults = postService.queryPostDetailByConditions(queryPostConditions);
            results = JSON.parseObject(JSON.toJSONString(postResults), PostInfoVo.class);
            responseResult.setData(results);
        }catch (Exception e){
            responseResult.setCode(ResultCode.INTERNAL_ERR.getCode());
            e.printStackTrace();
        }
        return responseResult;
    }

    @ApiOperation(value="发布帖子接口")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> createPost(@RequestBody NewPostVo newPostVo){
        logger.info("in PostController->createPost, get request:{}", newPostVo);
        ResponseResult<Integer> responseResult = new ResponseResult().setCode(ResultCode.SUCCESS.getCode());
        Integer results = 0;
        try{
            Post post = new Post();
            post.setTitle(newPostVo.getTitle());
            post.setContent(newPostVo.getContent());
            Usr author = new Usr();
            author.setOpenId(newPostVo.getUsrOpenId());
            post.setAuthor(author);
            Region region = new Region();
            region.setId(newPostVo.getRegionId());
            post.setAuthorRegion(region);
            Region hometownn = new Region();
            hometownn.setId(newPostVo.getHometownId());
            post.setAuthorHometown(hometownn);
            results = postService.createPost(post);
            responseResult.setData(results);
        }catch (Exception e){
            responseResult.setCode(ResultCode.INTERNAL_ERR.getCode());
            e.printStackTrace();
        }
        return responseResult;
    }

}
