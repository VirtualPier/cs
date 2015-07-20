package org.ligson.coderstar2.question.controllers;

import org.ligson.coderstar2.question.ask.service.QuestionAskService;
import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.user.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        List<List<SysTag>> questionTagList = questionService.findQuestionTagsByQuestionList(questionList);
        List<List<Category>> questionCategoryList = categoryService.findQuestionCategoryListByQuestionList(questionList);
        request.setAttribute("questionTagList", questionTagList);
        request.setAttribute("questionCategoryList", questionCategoryList);
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

    @RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
    public String saveQuestion(@RequestParam(value = "title", required = true) String title, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "tags", required = false) String tags, @RequestParam(value = "categoryIds", required = true) String categoryIds, @RequestParam(value = "money", required = false, defaultValue = "0") int money, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String[] categoryIdStringArray = categoryIds.split(",");
        long[] categoryIdArray = new long[categoryIdStringArray.length];
        for (int i = 0; i < categoryIdArray.length; i++) {
            categoryIdArray[i] = Long.parseLong(categoryIdStringArray[i]);
        }


        String[] tagArr = null;
        if (tags == null) {
            tagArr = new String[0];
        } else {
            tagArr = tags.split(";");
        }
        Map result = questionService.createQuestion(user, title, description, tagArr, categoryIdArray, money);
        boolean success = (boolean) result.get("success");
        if (success) {
            return "redirect:/index/index";
        } else {
            return "redirect:/question/create";
        }
    }

    @RequestMapping("/view")
    public String view(@RequestParam("id") long id, @RequestParam(value = "askSort", defaultValue = "supportNum", required = false) String askSort, HttpServletRequest request) {
        Question question = questionService.findQuestionById(id);
        List<Ask> asks = questionService.findQuestionAskList(question, askSort);
        List<SysTag> tags = questionService.findQuestionTagList(question);
        List<Category> categoryList = categoryService.findQuestionCategoryList(question);
        questionService.viewQuestion(question);
        Object object = request.getSession().getAttribute("user");
        boolean isAttention = false;
        if (object != null) {
            User user = (User) object;
            isAttention = questionService.isAttentionQuestion(user, question);
        }
        request.setAttribute("question", question);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("isAttention", isAttention);
        request.setAttribute("tags", tags);
        request.setAttribute("asks", asks);
        request.setAttribute("askSort", askSort);
        return "/question/view";
    }

    @RequestMapping("/saveAsk")
    public String saveAsk(@RequestParam("questionId") long questionId, @RequestParam("content") String content, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        questionService.saveAsk(user, questionId, content);
        return "redirect:/question/view?id=" + questionId;
    }

    @RequestMapping("/rateAsk")
    @ResponseBody
    public Map<String, Object> rateAsk(@RequestParam("askId") long askId, @RequestParam("upOrDown") String upOrDown, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return questionService.rateAsk(user, askId, upOrDown);
    }

    @RequestMapping("/attentionQuestion")
    @ResponseBody
    public Map<String, Object> attentionQuestion(@RequestParam("questionId") long questionId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return questionService.attentionQuestion(user, questionId);
    }

    @RequestMapping("/selectRightAsk")
    @ResponseBody
    public Map<String, Object> selectRightAsk(@RequestParam("id") long id) {
        return questionService.selectRightAsk(id);
    }

    @RequestMapping("/deleteAsk")
    public String deleteAsk(@RequestParam("id") long id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Ask ask = questionAskService.findAskById(id);
        Map<String, Object> result = questionAskService.deleteAsk(user, ask);
        return "redirect:/question/view?id=" + ask.getQuestion().getId();
    }


}
