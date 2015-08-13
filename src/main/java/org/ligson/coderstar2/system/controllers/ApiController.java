package org.ligson.coderstar2.system.controllers;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/8/13.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;
    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;

    @Qualifier("questionService")
    @Autowired
    private QuestionService questionService;

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

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/listCategory")
    @ResponseBody
    public Map<String, Object> listCategory() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        List<Category> categoryList = categoryService.list();
        result.put("categoryList", categoryList);
        return result;
    }

    @RequestMapping("/listArticleByCategory")
    @ResponseBody
    public Map<String, Object> listArticleByCategory(long categoryId, int offset, int max) {
        Category category = categoryService.findCategoryById(categoryId);
        List<Article> articleList = articleService.findAllArticleByCategory(category, offset, max);
        int total = articleService.countByCategory(category);
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("articleList", articleList);
        return result;
    }

    @RequestMapping("/listQuestionByCategory")
    @ResponseBody
    public Map<String, Object> listQuestionByCategory(long categoryId, int offset, int max) {
        Category category = categoryService.findCategoryById(categoryId);
        List<Question> questionList = questionService.findAllQuestionByCategory(category, offset, max);
        int total = questionService.countByCategory(category);
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("questionList", questionList);
        return result;
    }

    @RequestMapping("/viewArticle")
    @ResponseBody
    public Map<String, Object> viewArticle(long articleId) {
        Article article = articleService.findArticleById(articleId);
        Map<String, Object> result = new HashMap<>();
        result.put("article", article);
        return result;
    }

    @RequestMapping("/viewQuestion")
    @ResponseBody
    public Map<String, Object> viewQuestion(long questionId) {
        Question question = questionService.findQuestionById(questionId);
        Map<String, Object> result = new HashMap<>();
        result.put("question", question);
        return result;
    }

    @RequestMapping("/listArticleRemark")
    @ResponseBody
    public Map<String, Object> listArticleRemark(long articleId, int offset, int max) {
        return articleService.listArticleRemark(articleId, offset, max);
    }

    @RequestMapping("/listQuestionRemark")
    @ResponseBody
    public Map<String, Object> listQuestionRemark(long questionId, int offset, int max) {
        return questionService.listQuestionRemark(questionId, offset, max);
    }


}
