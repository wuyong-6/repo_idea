package com.wuy.service;

import com.github.pagehelper.PageInfo;
import com.wuy.bean.promotion.PromotionAd;
import com.wuy.bean.promotion.PromotionAdVo;

public interface PromotionAdService {
    /*
    分页获取所有的广告列表
    */
    public PageInfo findAllAdByPage(PromotionAdVo adVo);
    /*
       添加广告
     */
    void savePromotionAd(PromotionAd promotionAd);
    /*
       更新广告
    */
    void updatePromotionAd(PromotionAd promotionAd);
    /*
    回显广告信息
    */
    PromotionAd findPromotionAdById(int id);
    /*
        广告动态上下线
        */
    void updatePromotionAdStatus(int id, int status);


}
