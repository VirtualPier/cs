package org.ligson.coderstar2.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.pay.domains.Withdraw;
import org.ligson.coderstar2.pay.service.PayService;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.systag.service.SysTagService;
import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    @Qualifier("questionService")
    private QuestionService questionService;
    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;
    @Autowired
    @Qualifier("sysTagService")
    private SysTagService sysTagService;
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;

    @Autowired
    @Qualifier("payService")
    private PayService payService;

    public PayService getPayService() {
        return payService;
    }

    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public SysTagService getSysTagService() {
        return sysTagService;
    }

    public void setSysTagService(SysTagService sysTagService) {
        this.sysTagService = sysTagService;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        List<Question> newQuestionList = questionService.newestQuestion(5);
        List<Article> newArticleList = articleService.newestArticle(5);
        List<Category> categoryList = categoryService.list();
        List<Question> recommendQuestionList = questionService.questionListOrderBy(0, 3, "recommendNum", "desc");
        List<Article> recommendArticleList = articleService.articleListOrderBy(0, 3, "recommendNum", "desc");

        List<Question> hotQuestions = questionService.findHotQuestion(20);
        Map<String, Object> result = questionService.searchQuestion(-1, -1, false, "createDate", 20, 0);
        List<Question> waitQuestionList = (List<Question>) result.get("questionList");
        List<Question> offerQuestionList = questionService.findOfferQuesiton(20);
        List<Article> hotArticles = articleService.findHotArticle(20);
        List<User> hotAuthors = userService.hotAuthors(5);
        List<User> hotReplyers = userService.hotReplyers(5);
        List<Withdraw> withdrawList = payService.newestWithdraw(5);
        request.setAttribute("withdrawList", withdrawList);
        request.setAttribute("hotAuthors", hotAuthors);
        request.setAttribute("hotReplyers", hotReplyers);
        request.setAttribute("hotQuestions", hotQuestions);
        request.setAttribute("hotArticles", hotArticles);
        request.setAttribute("offerQuestionList", offerQuestionList);
        request.setAttribute("waitQuestionList", waitQuestionList);
        request.setAttribute("recommendQuestionList", recommendQuestionList);
        request.setAttribute("recommendArticleList", recommendArticleList);
        request.setAttribute("newQuestionList", newQuestionList);
        request.setAttribute("newArticleList", newArticleList);
        request.setAttribute("categoryList", categoryList);
        return "index/index";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
        if (name != null) {
            request.setAttribute("name", name);
        }
        if (msg != null) {
            request.setAttribute("msg", msg);
        }
        return "index/login";
    }

    @RequestMapping("/checkLogin")
    public String checkLogin(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password, HttpServletRequest request, Model model) {
        Map<String, Object> result = userService.login(name, password);
        boolean success = (boolean) result.get("success");
        if (success) {
            User user = (User) result.get("user");
            request.getSession().setAttribute("user", user);
            return "redirect:/index/index";
        } else {
            String msg = (String) result.get("msg");
            model.addAttribute("msg", msg);
            model.addAttribute("name", name);
            return "redirect:/index/login";
        }

    }

    @RequestMapping("/register")
    public String register(@RequestParam(value = "nickName", required = false) String nickName, @RequestParam(value = "cellphone", required = false) String cellphone, @RequestParam(value = "email", required = false) String email, HttpServletRequest request) {
        if (nickName != null) {
            request.setAttribute("nickName", nickName);
        }
        if (cellphone != null) {
            request.setAttribute("cellphone", cellphone);
        }
        if (email != null) {
            request.setAttribute("email", email);
        }
        return "index/register";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@RequestParam(value = "nickName") String nickName, @RequestParam(value = "cellphone") String cellphone, @RequestParam(value = "password") String password, @RequestParam(value = "email") String email, HttpServletRequest request) {
        Map<String, Object> result = userService.register(email, nickName, cellphone, password);
        boolean success = (boolean) result.get("success");
        if (success) {
            return "redirect:/index/login";
        } else {
            request.setAttribute("nickName", nickName);
            request.setAttribute("cellphone", cellphone);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            return "redirect:/index/register";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        Map<String, Object> result = userService.logout(request);
        boolean success = (boolean) result.get("success");
        return "redirect:/index/index";
    }

    @RequestMapping("/nologin")
    public String nologin(@RequestParam(value = "format", required = false) String format) {
        if ("html".equals(format) || format == null) {
            return "redirect:/index/login";
        } else {
            return "redirect:/index/responseJson";
        }
    }

    @RequestMapping("/responseJson")
    @ResponseBody
    public Map<String, Object> responseJson(@RequestParam(value = "success") boolean success, @RequestParam(value = "msg") String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }

    @RequestMapping("/ad")
    public String ad() {
        return "index/ad";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "index/contact";
    }

    @RequestMapping("/friendLinks")
    public String friendLinks() {
        return "index/friendLinks";
    }


    @RequestMapping("/searchKey")
    @ResponseBody
    public Map<String, Object> searchKey(String key, Integer type) {
        //返回的格式为[{value:}}
        Map<String, Object> map = new HashMap<>();
        int maxWorld = 10;
        //1、根据key进行在document中查找对应匹配关键字,根据类型获取对应关键字
        List<String> words = null;
        if (1 == type) {
            //question
            words = questionService.hotKey(key, maxWorld);
        } else {
            //article
            words = articleService.hotKey(key, maxWorld);
        }
        List<Map<String, String>> ll = new ArrayList<>();
        Map<String, String> hotKeyMap = new HashMap<>();
        for (String word : words) {
            hotKeyMap.put("value", word);
        }
        if (!hotKeyMap.isEmpty()) {
            ll.add(hotKeyMap);
        }
        map.put("hotKeys", ll);
        return map;
    }

    @RequestMapping("/search")
    public String search(@RequestParam(value = "searchType", defaultValue = "1") int searchType, @RequestParam(value = "title", required = false) String title, @RequestParam(value = "categoryId", defaultValue = "-1", required = false) long categoryId, @RequestParam(value = "tagId", defaultValue = "-1", required = false) long tagId, @RequestParam(value = "sort", defaultValue = "createDate", required = false) String sort, @RequestParam(value = "order", defaultValue = "desc", required = false) String order, @RequestParam(value = "offset", required = false, defaultValue = "0") int offset, @RequestParam(value = "max", defaultValue = "15", required = false) int max, HttpServletRequest request) {
        List modelList = null;
        int total = 0;
        if (searchType == 1) {
            Map<String, Object> result = questionService.searchQuestion(title, tagId, categoryId, max, offset, sort, order);
            modelList = (List) result.get("questionList");
            total = (int) result.get("total");
        } else {
            Map<String, Object> result = articleService.searchArticle(title, tagId, categoryId, max, offset, sort, order);
            modelList = (List) result.get("articleList");
            total = (int) result.get("total");
        }
        List<Category> categoryList = categoryService.list();
        List<List> tagCount = sysTagService.hotsTag(10);

        request.setAttribute("tagCount", tagCount);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("modelList", modelList);
        request.setAttribute("sort", sort);
        request.setAttribute("total", total);
        request.setAttribute("searchType", searchType);
        request.setAttribute("order", order);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("tagId", tagId);
        request.setAttribute("title", title);
        request.setAttribute("max", max);
        request.setAttribute("offset", offset);
        return "index/search";

    }

    @RequestMapping("/uploadFile")
    public void uploadFile(@RequestParam("upload") CommonsMultipartFile upload, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> result = userService.uploadFile(user, upload);
        return;
    }
}
