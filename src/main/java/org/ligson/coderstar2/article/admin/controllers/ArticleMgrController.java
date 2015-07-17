package org.ligson.coderstar2.article.admin.controllers;

import org.ligson.coderstar2.article.service.ArticleService;
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
@RequestMapping("/articleMgr")
public class ArticleMgrController {
    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;
    private static final String prefix = "admin/articleMgr/";

    @RequestMapping("/index")
    public String index() {
        return prefix + "index";
    }

    @RequestMapping("/articleList")
    @ResponseBody
    public Map<String, Object> articleList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "rows", defaultValue = "10") int rows) {
        int max = rows;
        int offset = (page - 1) * max;
        return articleService.list(offset, max);
    }
}
