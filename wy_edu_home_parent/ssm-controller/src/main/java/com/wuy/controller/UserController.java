package com.wuy.controller;

import com.github.pagehelper.PageInfo;
import com.wuy.bean.ResponseResult;
import com.wuy.bean.user.Role;
import com.wuy.bean.user.User;
import com.wuy.bean.user.UserVo;
import com.wuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo allUserByPage = userService.findAllUserByPage(userVo);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",allUserByPage);
        List list = allUserByPage.getList();
        System.out.println(list);

        return responseResult;
    }

    /**
     * 修改用户状态
     * ENABLE能登录，DISABLE不能登录
     * */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id , @RequestParam String status){
        if("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        }else {
            status = "ENABLE";
        }
        userService.updateUserStatus(id,status);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",status);
        return responseResult;
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User login = userService.login(user);
        ResponseResult result = null;
        if(login!=null){
//保存access_token
            Map<String,Object> map = new HashMap<>();
            String access_token = UUID.randomUUID().toString();
            map.put("access_token", access_token);
            map.put("user_id",login.getId());
            HttpSession session = request.getSession();
            session.setAttribute("user_id",login.getId());
            session.setAttribute("access_token",access_token);
            result = new ResponseResult(true,1,"响应成功",map);

        }else {
            result = new ResponseResult(true,1,"用户名密码错误",null);
        }
        return result;
    }

    /*
获取用户拥有的角色
*/
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(int id){
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"分配角色回显成功",roleList);
    }

    /*
分配角色
*/
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true,200,"分配角色成功",null);
    }

    /**
     * 获取用户权限
     * */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");

        if(token.equals(access_token)){
            int user_id = (Integer)session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        }else {
            ResponseResult result = new ResponseResult(false,400,"获取失败","");
            return result;
        }
    }

}
