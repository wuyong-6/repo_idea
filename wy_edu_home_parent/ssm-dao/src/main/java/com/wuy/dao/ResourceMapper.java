package com.wuy.dao;

import com.wuy.bean.user.Resource;
import com.wuy.bean.user.ResourceCategory;
import com.wuy.bean.user.ResourseVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceMapper {

    /*
    资源分类以及多条件查询
     */
    public List<Resource> findAllResource(ResourseVo resourceVo);
    /*
      查询所有资源分类
     */
    public List<ResourceCategory> findAllResourceCategory();

    public void saveResource(Resource resource);

    public void updateResource(Resource resource);

}
