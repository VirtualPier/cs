package org.ligson.coderstar2.user.controllers;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.pay.service.PayService;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.system.systag.service.SysTagService;
import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;
    @Autowired
    @Qualifier("payService")
    private PayService payService;
    @Autowired
    @Qualifier("questionService")
    private QuestionService questionService;

    @Autowired
    @Qualifier("sysTagService")
    private SysTagService sysTagService;

    public SysTagService getSysTagService() {
        return sysTagService;
    }

    public void setSysTagService(SysTagService sysTagService) {
        this.sysTagService = sysTagService;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public PayService getPayService() {
        return payService;
    }

    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/view")
    public String view(@RequestParam("id") long id, HttpServletRequest request) {
        User user = userService.findUserById(id);
        if (user != null) {
            List<Question> questionList = questionService.findAllQuestionByCreatorAndState(user, Question.STATE_PUBLISH, "createDate", "desc", 0, 10);
            List<Article> articleList = articleService.findAllArticleByCreatorAndState(user, Article.STATE_PUBLISH, "createDate", "desc", 0, 10);
            int questionTotal = questionService.countByCreatorAndState(user, Question.STATE_PUBLISH);
            int articleTotal = articleService.countByCreatorAndState(user, Question.STATE_PUBLISH);
            List<SysTag> sysTags = sysTagService.findUserGoodTag(user, 10);
            request.setAttribute("user", user);
            request.setAttribute("questionList", questionList);
            request.setAttribute("articleList", articleList);
            request.setAttribute("questionTotal", questionTotal);
            request.setAttribute("articleTotal", articleTotal);
            request.setAttribute("tags", sysTags);
            return "user/view";
        }
        return "404";
    }

    @RequestMapping("/loadUserArticles")
    @ResponseBody
    public Map<String, Object> loadUserArticles(@RequestParam("id") long id, @RequestParam("offset") int offset) {
        User user = userService.findUserById(id);
        List<Article> articleList = articleService.findAllArticleByCreatorAndState(user, Article.STATE_PUBLISH, "createDate", "desc", offset, 10);
        int total = articleService.countByCreatorAndState(user, Article.STATE_PUBLISH);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("total", total);
        result.put("articleList", articleList);
        return result;
    }

    @RequestMapping("/loadUserQuestions")
    @ResponseBody
    public Map<String, Object> loadUserQuestions(@RequestParam("id") long id, @RequestParam("offset") int offset) {
        User user = userService.findUserById(id);

        List<Question> questionList = questionService.findAllQuestionByCreatorAndState(user, Question.STATE_PUBLISH, "createDate", "desc", offset, 10);
        int total = questionService.countByCreatorAndState(user, Question.STATE_PUBLISH);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("total", total);
        result.put("questionList", questionList);
        return result;
    }

    @RequestMapping("/myPublish")
    public String myPublish() {
        return "user/myPublish";
    }

    @RequestMapping("/loadMyCreateQuestion")
    @ResponseBody
    public Map<String, Object> loadMyCreateQuestion(@RequestParam("offset") int offset, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Question> questionList = questionService.findAllQuestionByCreatorAndState(user, -1, "createDate", "desc", offset, 10);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("questionList", questionList);
        return result;
    }

    @RequestMapping("/myReply")
    public String myReply() {
        return "user/myReply";
    }

    @RequestMapping("/myAttention")
    public String myAttention() {
        return "user/myAttention";
    }

    @RequestMapping("/loadMyAttentionQuestion")
    @ResponseBody
    public Map<String, Object> loadMyAttentionQuestion(@RequestParam("offset") int offset, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Question> questionList = questionService.findAllAttentionQuestion(user, offset, 10);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("questionList", questionList);
        return result;
    }

    @RequestMapping("/myArticle")
    public String myArticle() {
        return "user/myArticle";
    }

    @RequestMapping("/loadMyArticle")
    @ResponseBody
    public Map<String, Object> loadMyArticle(@RequestParam("offset") int offset, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Article> articleList = articleService.findAllArticleByUser(user, offset, 10);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("articleList", articleList);
        return result;
    }


    @RequestMapping("/myAttentionArticle")
    public String myAttentionArticle() {
        return "user/myAttentionArticle";
    }

    @RequestMapping("/loadMyAttentionArticle")
    @ResponseBody
    public Map<String, Object> loadMyAttentionArticle(@RequestParam("offset") int offset, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Article> articleList = articleService.findAllAttentionArticle(user, offset, 10);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("articleList", articleList);
        return result;
    }


}
