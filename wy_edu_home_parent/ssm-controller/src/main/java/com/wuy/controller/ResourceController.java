package com.wuy.controller;

import com.github.pagehelper.PageInfo;
import com.wuy.bean.ResponseResult;
import com.wuy.bean.user.Resource;
import com.wuy.bean.user.ResourceCategory;
import com.wuy.bean.user.ResourseVo;
import com.wuy.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourseVo resourceVo){
        PageInfo<Resource> allResource = resourceService.findAllResource(resourceVo);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",allResource);
        return  responseResult;
    }

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceService.findAllResourceCategory();
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",allResourceCategory);
        return  responseResult;
    }



    }
