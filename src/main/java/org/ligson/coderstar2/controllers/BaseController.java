package org.ligson.coderstar2.controllers;

import org.ligson.coderstar2.user.domains.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ligson on 2015/11/9.
 */
public class BaseController {
    public HttpServletRequest request;
    public HttpServletResponse response;
    public HttpSession session;

    @ModelAttribute
    public void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    public void addAttribute(String key, Object object) {
        request.setAttribute(key, object);
    }

    public User readUser() {
        //session.getAttribute("")
        Object object;
        if (this.getClass().getName().contains("admin.controllers")) {
            object = session.getAttribute("adminUser");
        } else {
            object = session.getAttribute("user");
        }
        if (object != null) {
            return (User) object;
        } else {
            return null;
        }

    }
}
