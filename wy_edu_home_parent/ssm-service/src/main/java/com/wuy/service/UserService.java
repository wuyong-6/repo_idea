package com.wuy.service;

import com.github.pagehelper.PageInfo;
import com.wuy.bean.ResponseResult;
import com.wuy.bean.user.Role;
import com.wuy.bean.user.User;
import com.wuy.bean.user.UserVo;

import java.util.List;

public interface UserService {
    /*
    查询所有用户
    */
    public PageInfo findAllUserByPage(UserVo userVo);
    /*
     * 修改用户状态
     * */
    public void updateUserStatus(int id, String status);
    /*
    用户登录
    */
    public User login(User user) throws Exception;
    /**
     * 获取用户拥有的角色
     * */
    public List<Role> findUserRelationRoleById(int id) ;
    /*
    用户关联角色
    */
    void userContextRole(UserVo userVo);

    /*
     * 获取用户权限
     * */
    ResponseResult getUserPermissions(Integer id);

}
