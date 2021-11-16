package com.wuy.dao;

import com.wuy.bean.user.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /*
查询所有用户
*/
    public List<User> findAllUserByPage(UserVo userVo);
    /**
     * 修改用户状态
     * */
    public void updateUserStatus(@Param("id") int id,@Param("status") String status);
    /*
    用户登陆
    */
    public User login(User user);

    /*
    根据用户ID清空中间表
    */
    void deleteUserContextRole(Integer userId);
    /*
    分配角色
    */
    void userContextRole(User_Role_relation user_role_relation);

    /**
     * 根据ID查询用户当前角色
     * */
    public List<Role> findUserRelationRoleById(int id);
    /**
     * 根据角色id,查询角色拥有的顶级菜单信息
     * */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /**
     * 根据PID 查询子菜单信息
     * */
    public List<Menu> findSubMenuByPid(int pid);

    /**
     * 获取用户拥有的资源权限信息
     * */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

    public List<Resource> findResourceByRoleId2(List<Integer> ids);
    
    
    public void test1();
    public void test2();
    
    public void test11();
    public void test12();
    
    
    
    
    public void test3();
    public void test4(); 
    public void test5();
    public void test6();
    public void test7();
    public void test8();
}
