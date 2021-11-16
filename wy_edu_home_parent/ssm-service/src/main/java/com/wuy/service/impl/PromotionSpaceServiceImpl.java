package com.wuy.service.impl;

import com.wuy.bean.promotion.PromotionSpace;
import com.wuy.dao.PromotionSpaceMapper;
import com.wuy.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper spaceMapper;

    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        return spaceMapper.findAllPromotionSpace();
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);
        spaceMapper.savePromotionSpace(promotionSpace);
    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        Date date = new Date();
        promotionSpace.setUpdateTime(date);
        spaceMapper.updatePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        return spaceMapper.findPromotionSpaceById(id);
    }
}
