package com.qm.laoxiang.service.index.impl;

import com.qm.laoxiang.domain.BannerImage;
import com.qm.laoxiang.service.index.IndexService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService{
    @Override
    public List<BannerImage> queryIndexBannerImages() {
        List<BannerImage> bannerImages = new ArrayList<>();
        BannerImage bannerImage1 = new BannerImage();
        bannerImage1.setImageUrl("http://qijunrong.cn/banner/banner1.jpg");
        bannerImages.add(bannerImage1);
        BannerImage bannerImage2 = new BannerImage();
        bannerImage2.setImageUrl("http://qijunrong.cn/banner/banner2.jpg");
        bannerImages.add(bannerImage2);
        return bannerImages;
    }

    @Override
    public List<String> queryIndexScrollMsg() {
        List<String> scrollMsgs = new ArrayList<>();
        scrollMsgs.add("欢迎访问老乡同城情");
        scrollMsgs.add("本平台旨在分享旅游攻略");
        scrollMsgs.add("方便老乡们在异地体验更好的生活");
        scrollMsgs.add("与老乡分享旅游中的快乐！");
        return scrollMsgs;
    }
}
