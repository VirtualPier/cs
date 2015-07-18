package org.ligson.coderstar2.question.admin.controllers;

import org.ligson.coderstar2.question.service.QuestionService;
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
@RequestMapping("/questionMgr")
public class QuestionMgrController {
    private static final String prefix = "admin/questionMgr/";
    @Autowired
    @Qualifier("questionService")
    private QuestionService questionService;

    @RequestMapping("/index")
    public String index() {
        return prefix + "index";
    }

    @RequestMapping("/questionList")
    @ResponseBody
    public Map<String, Object> questionList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "rows", defaultValue = "10") int rows) {
        int max = rows;
        int offset = (page - 1) * max;
        return questionService.questionList(offset, max);
    }

    @RequestMapping("deleteQuestion")
    public Map<String, Object> deleteQuestion(@RequestParam("id") String id) {
        String[] idArray = id.split(",");
        long ids[] = new long[idArray.length];
        for (int i = 0; i < idArray.length; i++) {
            ids[i] = Long.parseLong(idArray[i]);
        }
        return questionService.deleteQuestion(ids);
    }

    @RequestMapping("/categoryMgr")
    public String categoryMgr() {
        return prefix + "categoryMgr";
    }
}
