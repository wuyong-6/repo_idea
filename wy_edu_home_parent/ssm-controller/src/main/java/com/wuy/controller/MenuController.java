package com.wuy.controller;

import com.wuy.bean.ResponseResult;
import com.wuy.bean.user.Menu;
import com.wuy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表信息
     * */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> list = menuService.findAllMenu();
        ResponseResult result = new ResponseResult(true,200,"响应成功",list);
        return result;
    }
    /**
     * 回显菜单信息(包括父子菜单的全部信息)
     * */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam int id){
        if(id == -1){
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",menuList);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        }else{
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menuList);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        }
    }

    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if(menu.getId() == null){
            menuService.saveMenu(menu);
        }else {
            menuService.updateMenu(menu);
        }
        ResponseResult result = new ResponseResult(true,200,"响应成功",menu);
        return result;
    }




}
