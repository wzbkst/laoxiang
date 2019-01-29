package com.qm.laoxiang.service.index;

import com.qm.laoxiang.domain.BannerImage;

import java.util.List;

public interface IndexService {
    List<BannerImage> queryIndexBannerImages();

    List<String> queryIndexScrollMsg();
}
