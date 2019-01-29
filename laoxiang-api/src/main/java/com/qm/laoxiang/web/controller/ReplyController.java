package com.qm.laoxiang.web.controller;

import com.alibaba.fastjson.JSON;
import com.qm.laoxiang.api.domain.vo.NewReplyVo;
import com.qm.laoxiang.api.domain.vo.ReplyInfoVo;
import com.qm.laoxiang.common.web.domain.ResponseResult;
import com.qm.laoxiang.common.web.domain.ResultCode;
import com.qm.laoxiang.common.web.domain.query.QueryReplyConditions;
import com.qm.laoxiang.domain.NewReply;
import com.qm.laoxiang.domain.Reply;
import com.qm.laoxiang.domain.utils.PageBean;
import com.qm.laoxiang.service.reply.ReplyService;
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
@Api(value="帖子回复相关接口")
@Controller
@RequestMapping("api/v1/reply")
public class ReplyController {
    private Logger logger= LoggerFactory.getLogger(ReplyController.class);

    @Autowired
    private ReplyService replyService;

    @ApiOperation(value="查询帖子回复列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<PageBean<ReplyInfoVo>> getReplies(int currentPage, int pageSize, QueryReplyConditions queryReplyConditions){
        logger.info("in ReplyController->getReplies, get request:");
        ResponseResult<PageBean<ReplyInfoVo>> responseResult = new ResponseResult().setCode(ResultCode.SUCCESS.getCode());
        PageBean<ReplyInfoVo> results = new PageBean<>();
        try{
            PageBean<Reply> replyResults = replyService.queryReplyByPageAndConditions(currentPage, pageSize, queryReplyConditions);
            BeanUtils.copyProperties(replyResults, results);
            if(CollectionUtils.isNotEmpty(replyResults.getItems())){
                results.setItems(replyResults.getItems().stream().map(reply -> {
                    ReplyInfoVo replyInfoVo = JSON.parseObject(JSON.toJSONString(reply), ReplyInfoVo.class);
                    return replyInfoVo;
                }).collect(Collectors.toList()));
            }
            responseResult.setData(results);
        }catch (Exception e){
            responseResult.setCode(ResultCode.INTERNAL_ERR.getCode());
            e.printStackTrace();
        }
        return responseResult;
    }

    @ApiOperation(value="回复帖子接口")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<Integer> createReply(@RequestBody NewReplyVo newReplyVo){
        logger.info("in ReplyController->createReply, get request:{}", newReplyVo);
        ResponseResult<Integer> responseResult = new ResponseResult().setCode(ResultCode.SUCCESS.getCode());
        Integer results = 0;
        try{
            NewReply newReply = new NewReply();
            BeanUtils.copyProperties(newReplyVo, newReply);
            results = replyService.createReply(newReply);
            responseResult.setData(results);
        }catch (Exception e){
            responseResult.setCode(ResultCode.INTERNAL_ERR.getCode());
            e.printStackTrace();
        }
        return responseResult;
    }

}
