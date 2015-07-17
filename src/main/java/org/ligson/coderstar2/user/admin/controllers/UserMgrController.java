package org.ligson.coderstar2.user.admin.controllers;

import org.ligson.coderstar2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
