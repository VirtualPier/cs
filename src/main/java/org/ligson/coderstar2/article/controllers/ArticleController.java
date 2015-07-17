package org.ligson.coderstar2.article.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ligson on 2015/7/17.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
