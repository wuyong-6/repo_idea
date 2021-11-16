package com.wuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuy.bean.user.Resource;
import com.wuy.bean.user.ResourceCategory;
import com.wuy.bean.user.ResourseVo;
import com.wuy.dao.ResourceMapper;
import com.wuy.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResource(ResourseVo resourceVo) {
        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());
        List<Resource> allResource = resourceMapper.findAllResource(resourceVo);
        PageInfo<Resource> resourcePageInfo = new PageInfo<>(allResource);
        return resourcePageInfo;
    }

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceMapper.findAllResourceCategory();
    }
}
