package com.wuy.dao;

import com.wuy.bean.user.Role;
import com.wuy.bean.user.Role_menu_relation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    /*
    查询角色列表(条件)
    */
    public List<Role> findAllRole(Role role);

    /*
    根据角色ID查询菜单信息
    */
    List<String> findMenuByRoleId(Integer roleId);
    /*
    根据roid 清空关系表的role_menu_relation的信息
     */
    void deleteRoleContextMenu(Integer rid);
    /*
   角色菜单关联
   */
    void RoleContextMenu(Role_menu_relation role_menu_relation);
    /*
    删除角色
    */
    void deleteRole(Integer id);
}
