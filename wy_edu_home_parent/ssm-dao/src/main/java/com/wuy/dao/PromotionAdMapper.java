package com.wuy.dao;

import com.wuy.bean.promotion.PromotionAd;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionAdMapper {
    /*
    分页获取所有的广告列表
    */
    public List<PromotionAd> findAllAdByPage();
    /*
       广告添加
     */
    void savePromotionAd(PromotionAd promotionAd);
    /*
       广告更新
    */
    void updatePromotionAd(PromotionAd promotionAd);
    /**
     * 根据id查询广告信息
     * */
    PromotionAd findPromotionAdById(int id);
    /**
     * 广告的动态上下线
     * */
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
