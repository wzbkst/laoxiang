package com.qm.laoxiang.service.reply.impl;

import com.qm.laoxiang.common.exception.ServiceException;
import com.qm.laoxiang.common.web.domain.query.QueryReplyConditions;
import com.qm.laoxiang.dao.mapper.HometownMapper;
import com.qm.laoxiang.dao.mapper.PostInfoMapper;
import com.qm.laoxiang.dao.mapper.RegionMapper;
import com.qm.laoxiang.dao.mapper.ReplyInfoMapper;
import com.qm.laoxiang.domain.NewReply;
import com.qm.laoxiang.domain.Region;
import com.qm.laoxiang.domain.Reply;
import com.qm.laoxiang.domain.Usr;
import com.qm.laoxiang.domain.db.Hometown;
import com.qm.laoxiang.domain.db.ReplyInfo;
import com.qm.laoxiang.domain.db.ReplyInfoExample;
import com.qm.laoxiang.domain.utils.PageBean;
import com.qm.laoxiang.service.reply.ReplyService;
import com.qm.laoxiang.service.user.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private PostInfoMapper postInfoMapper;
    @Autowired
    private ReplyInfoMapper replyInfoMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private HometownMapper hometownMapper;
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public PageBean<Reply> queryReplyByPageAndConditions(int currentPage, int pageSize, QueryReplyConditions queryReplyConditions) {
        ReplyInfoExample query = new ReplyInfoExample();
        query.createCriteria().andYnEqualTo(1);
        if(queryReplyConditions.getPostId()!= null && queryReplyConditions.getPostId()> 0){
            query.getOredCriteria().get(0).andPostIdEqualTo(queryReplyConditions.getPostId().longValue());
        }
        query.setOrderByClause("created desc");
        List<ReplyInfo> allRepliesInDB = replyInfoMapper.selectByExample(query);
        long countNums = replyInfoMapper.countByExample(query);            //总记录数
        PageBean<Reply> pageData = new PageBean(currentPage, pageSize, countNums);
        List<Reply> allReplies = new ArrayList<>();
        if( ! CollectionUtils.isEmpty(allRepliesInDB)){
            allReplies = allRepliesInDB.stream().map(replyInDB -> {
                Reply reply = new Reply();
                BeanUtils.copyProperties(replyInDB, reply);
                try {
                    Usr usr = userService.getUserDetail(replyInDB.getUsrId());
                    reply.setAuthor(usr);
                    Hometown hometownInDb = hometownMapper.selectByPrimaryKey(replyInDB.getHometownId());
                    Region hometown = new Region();
                    hometown.setId(replyInDB.getHometownId());
                    hometown.setName(hometownInDb.getName());
                    reply.setAuthorHometown(hometown);
                    com.qm.laoxiang.domain.db.Region regionInDb = regionMapper.selectByPrimaryKey(replyInDB.getRegionId());
                    Region region = new Region();
                    region.setId(replyInDB.getRegionId());
                    region.setName(regionInDb.getName());
                    reply.setAuthorRegion(region);
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return null;
                }
                return reply;
            }).collect(Collectors.toList());
        }
        pageData.setItems(allReplies);
        return pageData;
    }

    @Override
    public int createReply(NewReply newReply) throws ServiceException {
        Long userId = userService.getUserIdByOpenId(newReply.getOpenId());
//        Usr usr = userService.getUserDetail(userId);
        Date now = new Date();
        ReplyInfo replyInfo = new ReplyInfo();
        replyInfo.setCreated(now);
        replyInfo.setModified(now);
        replyInfo.setYn(1);
        replyInfo.setPraiseCnt(0);
        replyInfo.setContent(newReply.getContent());
        replyInfo.setHometownId(newReply.getHometownId());
        replyInfo.setRegionId(newReply.getRegionId());
        replyInfo.setUsrId(userId);
        replyInfo.setPostId(newReply.getPostId());
        replyInfo.setReplyId(newReply.getReplyId());
        return replyInfoMapper.insert(replyInfo);
    }
}
