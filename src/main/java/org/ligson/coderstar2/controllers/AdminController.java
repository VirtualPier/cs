package org.ligson.coderstar2.controllers;

import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by ligson on 2015/7/17.
 * 后台业务
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

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
    public String index(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
        if (name != null) {
            request.setAttribute("name", name);
        }
        if (msg != null) {
            request.setAttribute("msg", msg);
        }
        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model) {
        Map<String, Object> result = userService.login(name, password);
        boolean success = (boolean) result.get("success");
        if (success) {
            User user = (User) result.get("user");
            session.setAttribute("adminUser", user);
            return "redirect:/articleMgr/index";
        } else {
            String msg = (String) result.get("msg");
            model.addAttribute("name", name);
            model.addAttribute("msg", msg);
            return "redirect:/admin/index";
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/admin/index";
    }
}
