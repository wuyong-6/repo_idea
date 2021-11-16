package com.wuy.service;

import com.wuy.bean.user.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> findSubMenuListByPid(int pid);

    public List<Menu> findAllMenu();

    Menu findMenuById(Integer id);

    public void saveMenu(Menu menu);

    public void updateMenu(Menu menu);


}
