package com.wuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuy.bean.ResponseResult;
import com.wuy.bean.user.*;
import com.wuy.dao.UserMapper;
import com.wuy.service.UserService;
import com.wuy.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);
        PageInfo<User> userPageInfo = new PageInfo<>(allUserByPage);
        return userPageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {
        userMapper.updateUserStatus(id,status);
    }

    /**
     * 用户登录
     * */

    @Override
    public User login(User user) throws Exception {
        User user2 = userMapper.login(user);
        if(user2!=null && Md5.verify(user.getPassword(),"lagou",user2.getPassword())){
            return user2;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(int id) {
        return userMapper.findUserRelationRoleById(id);
    }

    @Override
    public void userContextRole(UserVo userVo) {
        userMapper.deleteUserContextRole(userVo.getUserId());
        for (Integer roleId:userVo.getRoleIdList()){
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer id) {
        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        //2.获取角色ID,保存到 list
        List<Integer> list = new ArrayList<>();
        for (Role role : roleList) {
            list.add(role.getId());
        }
        //3.根据角色id查询 父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(list);

        //4.封装父菜单下的子菜单
        for (Menu m:parentMenu){
            List<Menu> subMenuByPid = userMapper.findSubMenuByPid(m.getId());
            m.setSubMenuList(subMenuByPid);
        }

        //5.获取资源权
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);

        //6.封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",parentMenu); //menuList: 菜单权限数据
        map.put("resourceList",resourceList);//resourceList: 资源权限数据
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }
}
