package org.ligson.coderstar2.controllers;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.service.ArticleService;
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
        List<Question> questionList = questionService.findHotQuestion(5);
        List<Article> articleList = articleService.findHotArticle(5);
        List<List> tagCount = sysTagService.hotsTag(5);
        List<Question> offerQuestionList = questionService.findOfferQuesiton(5);
        List<Question> newQuestionList = questionService.newestQuestion(5);
        List<Article> newArticleList = articleService.newestArticle(5);
        List<Category> categoryList = categoryService.list();
        List<List<Question>> questionCategoryList = new ArrayList<>();
        List<List<Article>> articleCategoryList = new ArrayList<>();
        for (Category category : categoryList) {
            List<Question> questionList1 = questionService.findAllQuestionByCategory(category, 0, 5);
            List<Article> articleList1 = articleService.findAllArticleByCategory(category, 0, 5);

            questionCategoryList.add(questionList1);
            articleCategoryList.add(articleList1);
        }

        request.setAttribute("questionList", questionList);
        request.setAttribute("articleList", articleList);
        request.setAttribute("tagCount", tagCount);
        request.setAttribute("offerQuestionList", offerQuestionList);
        request.setAttribute("newQuestionList", newQuestionList);
        request.setAttribute("newArticleList", newArticleList);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("questionCategoryList", questionCategoryList);
        request.setAttribute("articleCategoryList", articleCategoryList);

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
        return;
    }
}
