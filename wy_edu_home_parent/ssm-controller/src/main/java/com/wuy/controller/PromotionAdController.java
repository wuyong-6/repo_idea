package com.wuy.controller;

import com.github.pagehelper.PageInfo;
import com.wuy.bean.ResponseResult;
import com.wuy.bean.promotion.PromotionAd;
import com.wuy.bean.promotion.PromotionAdVo;
import com.wuy.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;

    /*
    分页查询所有广告信息
    */
    @RequestMapping("/findAllPromotionAd")
    public ResponseResult findAllAdByPage(PromotionAdVo adVo) {
        PageInfo<PromotionAdVo> allAdByPage = promotionAdService.findAllAdByPage(adVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", allAdByPage);
        return responseResult;
    }

    /**
     * 广告图片上传
     * */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult promotionAdUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        try {
            if(file.isEmpty()){
                throw  new RuntimeException("文件不能为空!!");
            }
            //D:\Code\idea\project\wy_edu_home_parent\ssm-controller\target\ssm-controller-1.0-SNAPSHOT\
            String realPath = request.getServletContext().getRealPath("/");
            //D:\Code\idea\project\wy_edu_home_parent\
            String webPath = realPath.substring(0, realPath.indexOf("ssm-controller"));

            String originalFilename = file.getOriginalFilename();
            String newName=System.currentTimeMillis()+originalFilename.substring(originalFilename.indexOf("."));

            String uploadPath=webPath+"upload\\";
            File filePath = new File(uploadPath, newName);
            if(! filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录: " + filePath);
            }
            file.transferTo(filePath);

            Map<String,String> map = new HashMap<>();
            map.put("fileName",newName);
            map.put("filePath",webPath+"/upload/"+newName);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        try {
            if(promotionAd.getId() == null){
                Date date = new Date();
                promotionAd.setCreateTime(date);
                promotionAd.setUpdateTime(date);
                promotionAdService.savePromotionAd(promotionAd);
            }else{
                Date date = new Date();
                promotionAd.setUpdateTime(date);
                promotionAdService.updatePromotionAd(promotionAd);
            }
            return new ResponseResult(true, 200, "响应成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据id回显 广告数据
     * */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam int id){
        try {
            PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
            ResponseResult result = new ResponseResult(true,200,"响应成功",promotionAd);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
    广告位置上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id, @RequestParam int status) {
        //执行修改操作
        if (status == 1) {
            promotionAdService.updatePromotionAdStatus(id, status);
        } else {
            promotionAdService.updatePromotionAdStatus(id, 0);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;

    }


}
