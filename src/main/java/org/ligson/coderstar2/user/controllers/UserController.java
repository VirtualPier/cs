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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
}
