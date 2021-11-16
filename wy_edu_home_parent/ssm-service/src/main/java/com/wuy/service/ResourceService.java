package com.wuy.service;

import com.github.pagehelper.PageInfo;
import com.wuy.bean.user.Resource;
import com.wuy.bean.user.ResourceCategory;
import com.wuy.bean.user.ResourseVo;

import java.util.List;

public interface ResourceService {
    /*
    资源分类以及多条件查询
     */
    public PageInfo<Resource> findAllResource(ResourseVo resourceVo);
    /*
        查询所有资源分类
       */
    public List<ResourceCategory> findAllResourceCategory();
}
