package org.ligson.coderstar2.question.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ligson on 2015/7/17.
 */
@Controller
@RequestMapping("/questionMgr")
public class QuestionMgrController {
    private static final String prefix = "admin/questionMgr/";

    @RequestMapping("/index")
    public String index() {
        return prefix + "index";
    }

    @RequestMapping("/categoryMgr")
    public String categoryMgr() {
        return prefix + "categoryMgr";
    }
}
