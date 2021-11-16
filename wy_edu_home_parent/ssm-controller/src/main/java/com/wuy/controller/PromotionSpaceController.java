package com.wuy.controller;

import com.wuy.bean.ResponseResult;
import com.wuy.bean.promotion.PromotionAd;
import com.wuy.bean.promotion.PromotionSpace;
import com.wuy.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService spaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> allPromotionSpace = spaceService.findAllPromotionSpace();
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",allPromotionSpace);
        return responseResult;
    }

    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult savePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if(promotionSpace.getId() == null){
            spaceService.savePromotionSpace(promotionSpace);

        }else{
            spaceService.updatePromotionSpace(promotionSpace);
        }
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功","");
        return responseResult;
    }
    /**
     * 根据id查询 广告位信息
     * */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
        PromotionSpace promotionSpace = spaceService.findPromotionSpaceById(id);
        ResponseResult result = new ResponseResult(true,200,"响应成功",promotionSpace);
        return result;
    }

}
