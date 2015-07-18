package org.ligson.coderstar2.question.controllers;

import org.ligson.coderstar2.question.ask.service.QuestionAskService;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/index")
    public String index(@RequestParam(value = "hasDeal", defaultValue = "false", required = false) boolean hasDeal, @RequestParam(value = "sort", defaultValue = "money", required = false) String sort, @RequestParam(value = "max", defaultValue = "15", required = false) int max, @RequestParam(value = "offset", defaultValue = "0", required = false) int offset, HttpServletRequest request) {
        Map<String, Object> map = questionService.searchQuestion(hasDeal, sort, max, offset);
        int total = (int) map.get("total");
        List<Question> questionList = (List<Question>) map.get("questionList");
        request.setAttribute("total", total);
        request.setAttribute("offset", offset);
        request.setAttribute("hasDeal", ((Boolean) hasDeal).toString());
        request.setAttribute("sort", sort);
        request.setAttribute("max", max);
        request.setAttribute("questionList", questionList);
        List<Category> categoryList = categoryService.list();
        request.setAttribute("categoryList", categoryList);
        return "question/index";
    }

    @RequestMapping("/create")
    public String create(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "tags", required = false) String tags, @RequestParam(value = "categoryIds", required = false) String categoryIds, HttpServletRequest request) {
        List<Category> categoryList = categoryService.list();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("title", title);
        request.setAttribute("description", description);
        request.setAttribute("tags", tags);
        request.setAttribute("categoryIds", categoryIds);
        return "question/create";
    }

    @RequestMapping("/saveQuestion")
    public String saveQuestion(@RequestParam(value = "title", required = true) String title, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "tags", required = false) String tags, @RequestParam(value = "categoryIds", required = true) String categoryIds, @RequestParam(value = "money", required = false) double money, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String[] categoryIdStringArray = categoryIds.split(",");
        long[] categoryIdArray = new long[categoryIdStringArray.length];
        for (int i = 0; i < categoryIdArray.length; i++) {
            categoryIdArray[i] = Long.parseLong(categoryIdStringArray[i]);
        }
        Map result = questionService.createQuestion(user, title, description, tags, categoryIdArray, money);
        boolean success = (boolean) result.get("success");
        if (success) {
            return "redirect:/index/index";
        } else {
            return "redirect:/question/create";
        }
    }
}
