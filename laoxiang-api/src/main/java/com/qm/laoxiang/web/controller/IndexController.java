package com.qm.laoxiang.web.controller;

import com.qm.laoxiang.api.domain.vo.BannerImageVo;
import com.qm.laoxiang.common.web.domain.ResponseResult;
import com.qm.laoxiang.common.web.domain.ResultCode;
import com.qm.laoxiang.domain.BannerImage;
import com.qm.laoxiang.service.index.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * hello
 */
@Api(value="首页接口")
@Controller
@RequestMapping("api/v1/index")
public class IndexController {
    private Logger logger= LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexService indexService;


    @ApiOperation(value="hello接口")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        logger.info("index");
        return "index";
    }

    @ApiOperation(value="首页banner查询接口")
    @RequestMapping(value = "/banner", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<List<BannerImageVo>> getBanner(){
        logger.info("in IndexController->banner, get request:");
        ResponseResult<List<BannerImageVo>> responseResult = new ResponseResult().setCode(ResultCode.SUCCESS.getCode());
        List<BannerImageVo> results = new ArrayList<>();
        try{
            List<BannerImage> imageResults = indexService.queryIndexBannerImages();
            if(CollectionUtils.isNotEmpty(imageResults)){
                results = imageResults.stream().map(bannerImage -> {
                    BannerImageVo bannerImageVo = new BannerImageVo();
                    bannerImageVo.setDesc(bannerImage.getDesc());
                    bannerImageVo.setImageUrl(bannerImage.getImageUrl());
                    bannerImageVo.setSrcUrl(bannerImage.getSrcUrl());
                    return bannerImageVo;
                }).collect(Collectors.toList());
            }
            responseResult.setData(results);
        }catch (Exception e){
            responseResult.setCode(ResultCode.INTERNAL_ERR.getCode());
            e.printStackTrace();
        }
        return responseResult;
    }

    @ApiOperation(value="首页轮播信息查询接口")
    @RequestMapping(value = "/scrollmsg", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<List<String>> getScrollMsg(){
        logger.info("in IndexController->scroll, get request:");
        ResponseResult<List<String>> responseResult = new ResponseResult().setCode(ResultCode.SUCCESS.getCode());
        List<String> results = new ArrayList<>();
        try{
            results = indexService.queryIndexScrollMsg();
            responseResult.setData(results);
        }catch (Exception e){
            responseResult.setCode(ResultCode.INTERNAL_ERR.getCode());
            e.printStackTrace();
        }
        return responseResult;
    }
}
