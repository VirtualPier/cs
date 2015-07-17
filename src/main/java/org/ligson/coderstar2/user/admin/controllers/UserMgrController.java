package org.ligson.coderstar2.user.admin.controllers;

import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/17.
 */
@Controller
@RequestMapping("/userMgr")
public class UserMgrController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/index")
    public String index() {
        return "admin/userMgr/index";
    }

    @RequestMapping("/userList")
    @ResponseBody
    public Map<String, Object> userList(@RequestParam("page") int page, @RequestParam("rows") int rows) {
        int max = rows;
        int offset = (page - 1) * max;
        Map<String, Object> result = userService.listUser(offset, max);
        return result;
    }

    @RequestMapping("/editeUser")
    @ResponseBody
    public Map<String, Object> editeUser(@RequestParam("id") String id, @RequestParam("state") int state) {
        String[] sIds=id.split(",");
        long[] ids = new long[sIds.length];
        for (int i=0;i<sIds.length;i++){
            ids[i] =Long.parseLong(sIds[i]);
        }
        return  userService.modifyUserState(ids,state);
    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    public Map<String, Object> resetPassword(@RequestParam("userId") long userId,@RequestParam("new_password") String new_password,HttpServletRequest request) {
        User user= (User) request.getSession().getAttribute("adminUser");
        Map<String, Object> result = userService.resetPassword(user, userId, null, new_password);
        return result;
    }
}
