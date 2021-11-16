package com.wuy.dao;

import com.wuy.bean.user.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    /**
     * 查询所有父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询菜单信息
     */
    public List<Menu> findAllMenu();

    Menu findMenuById(Integer id);

    public void saveMenu(Menu menu);

    public void updateMenu(Menu menu);


}
