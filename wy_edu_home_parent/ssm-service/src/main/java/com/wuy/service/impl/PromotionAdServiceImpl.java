package com.wuy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuy.bean.promotion.PromotionAd;
import com.wuy.bean.promotion.PromotionAdVo;
import com.wuy.dao.PromotionAdMapper;
import com.wuy.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {
    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo findAllAdByPage(PromotionAdVo adVo) {
        PageHelper.startPage(adVo.getCurrentPage(), adVo.getPageSize());
        List<PromotionAd> allAdByPage = promotionAdMapper.findAllAdByPage();
        PageInfo<PromotionAd> adPageInfo = new PageInfo<>(allAdByPage);
        return adPageInfo;
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(int id) {
        return promotionAdMapper.findPromotionAdById(id);
    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
