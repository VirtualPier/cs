package org.ligson.coderstar2.question.admin.controllers;

import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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
    @ResponseBody
    public Map<String,Object> deleteQuestion(@RequestParam("ids") String ids){
        String[] sIds=ids.split(",");
        long[] lIds = new long[sIds.length];
        for (int i=0;i<sIds.length;i++){
            lIds[i] =Long.parseLong(sIds[i]);
        }
        return questionService.deleteQuestion(lIds);
    }

    @RequestMapping("/editeQuestion")
    @ResponseBody
    public Map<String,Object> editeQuestion(@RequestParam("id") String id){
        String[] sIds=id.split(",");
        long[] lIds = new long[sIds.length];
        for (int i=0;i<sIds.length;i++){
            lIds[i] =Long.parseLong(sIds[i]);
        }
        Map<String, Object> result=questionService.modifyQuestionState(lIds, Question.STATE_PUBLISH);
        return result;
    }

    @RequestMapping("/syncQuestionIndex")
    @ResponseBody
    public Map<String,Object> syncQuestionIndex() {
        Map<String,Object> result=questionService.syncQuestionIndex();
        return result;
    }

    @RequestMapping("/categoryMgr")
    public String categoryMgr() {
        return prefix + "categoryMgr";
    }
}
