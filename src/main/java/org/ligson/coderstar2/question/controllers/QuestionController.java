package org.ligson.coderstar2.question.controllers;

import org.ligson.coderstar2.question.ask.service.QuestionAskService;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ligson on 2015/7/17.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    @Qualifier("questionService")
    private QuestionService questionService;
    @Autowired
    @Qualifier("questionAskService")
    private QuestionAskService questionAskService;
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;

    public QuestionService getQuestionService() {
        return questionService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public QuestionAskService getQuestionAskService() {
        return questionAskService;
    }

    public void setQuestionAskService(QuestionAskService questionAskService) {
        this.questionAskService = questionAskService;
    }

    public String create(HttpServletRequest request) {
        List<Category> categoryList = categoryService.list();
        request.setAttribute("categoryList",categoryList);
        return "question/create";
    }
}
