package org.ligson.coderstar2.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ligson on 2015/11/9.
 */
public class BaseController {
    private HttpServletRequest request;
    private HttpServletResponse response;

    @ModelAttribute
    public void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void addAttribute(String key, Object object) {
        request.setAttribute(key, object);
    }
}
