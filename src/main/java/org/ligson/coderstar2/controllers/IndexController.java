package org.ligson.coderstar2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ligson on 2015/7/16.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "index/index";
    }
}
