package com.wuy.service.impl;

import com.wuy.bean.user.Role;
import com.wuy.bean.user.RoleMenuVo;
import com.wuy.bean.user.Role_menu_relation;
import com.wuy.dao.RoleMapper;
import com.wuy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleManager;

    @Override
    public List<Role> findAllRole(Role role) {
        return roleManager.findAllRole(role);
    }

    @Override
    public List<String> findMenuByRoleId(Integer roleId) {
        return roleManager.findMenuByRoleId(roleId);
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        roleManager.deleteRoleContextMenu(roleMenuVo.getRoleId());
        for(Integer mid:roleMenuVo.getMenuIdList()){
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setMenuId(mid);

            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleManager.RoleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer id) {
        roleManager.deleteRoleContextMenu(id);
        roleManager.deleteRole(id);
    }
}
