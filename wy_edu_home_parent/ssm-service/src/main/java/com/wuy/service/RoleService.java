package com.wuy.service;

import com.wuy.bean.user.Role;
import com.wuy.bean.user.RoleMenuVo;
import com.wuy.bean.user.Role_menu_relation;

import java.util.List;

public interface RoleService {
    public List<Role> findAllRole(Role role);
    /*
   根据角色ID查询菜单信息
   */
    List<String> findMenuByRoleId(Integer roleId);

    void roleContextMenu(RoleMenuVo roleMenuVo);

    void deleteRole(Integer id);
}
