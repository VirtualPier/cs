package org.ligson.coderstar2.controllers;

import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
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

        return "index/index";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
        if (name != null) {
            request.setAttribute("name", name);
        }
        if (msg != null) {
            request.setAttribute("msg", msg);
        }
        return "index/login";
    }

    @RequestMapping("/checkLogin")
    public String checkLogin(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password, HttpServletRequest request, Model model) {
        Map<String, Object> result = userService.login(name, password);
        boolean success = (boolean) result.get("success");
        if (success) {
            User user = (User) result.get("user");
            request.getSession().setAttribute("user", user);
            return "redirect:/index/index";
        } else {
            String msg = (String) result.get("msg");
            model.addAttribute("msg", msg);
            model.addAttribute("name", name);
            return "redirect:/index/login";
        }

    }

    @RequestMapping("/register")
    public String register(@RequestParam(value = "nickName", required = false) String nickName, @RequestParam(value = "cellphone", required = false) String cellphone, @RequestParam(value = "email", required = false) String email, HttpServletRequest request) {
        if (nickName != null) {
            request.setAttribute("nickName", nickName);
        }
        if (cellphone != null) {
            request.setAttribute("cellphone", cellphone);
        }
        if (email != null) {
            request.setAttribute("email", email);
        }
        return "index/register";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@RequestParam(value = "nickName") String nickName, @RequestParam(value = "cellphone") String cellphone, @RequestParam(value = "password") String password, @RequestParam(value = "email") String email, HttpServletRequest request) {
        Map<String, Object> result = userService.register(email, nickName, cellphone, password);
        boolean success = (boolean) result.get("success");
        if (success) {
            return "redirect:/index/login";
        } else {
            request.setAttribute("nickName", nickName);
            request.setAttribute("cellphone", cellphone);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            return "redirect:/index/register";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        Map<String, Object> result = userService.logout(request);
        boolean success = (boolean) result.get("success");
        return "redirect:/index/index";
    }

    @RequestMapping("/nologin")
    public String nologin(@RequestParam(value = "format", required = false) String format) {
        if ("html".equals(format) || format == null) {
            return "redirect:/index/login";
        } else {
            return "redirect:/index/responseJson";
        }
    }

    @RequestMapping("/responseJson")
    @ResponseBody
    public Map<String, Object> responseJson(@RequestParam(value = "success") boolean success, @RequestParam(value = "msg") String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }

    @RequestMapping("/ad")
    public String ad() {
        return "index/ad";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "index/contact";
    }

    @RequestMapping("/friendLinks")
    public String friendLinks() {
        return "index/friendLinks";
    }
}
