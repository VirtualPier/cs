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
 * 系统对外接口
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

    /***
     * 获取分类列表
     *
     * @return categoryList的json数据
     */
    @RequestMapping("/listCategory")
    @ResponseBody
    public Map<String, Object> listCategory() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        List<Category> categoryList = categoryService.list();
        result.put("categoryList", categoryList);
        return result;
    }

    /***
     * 根据分类查找文章
     *
     * @param categoryId 分类id
     * @param offset     开始记录数
     * @param max        查询多少条记录
     * @return {total:总记录数,articleList:文章列表}
     */
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

    /***
     * 根据分类查找分类下的问题
     *
     * @param categoryId 分类id
     * @param offset     开始记录偏移量
     * @param max        一页有多少条记录
     * @return {total:记录总数,questionList:问题列表}
     */
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

    /***
     * 查看文章内容
     *
     * @param articleId 文章id
     * @return 文章信息
     */
    @RequestMapping("/viewArticle")
    @ResponseBody
    public Map<String, Object> viewArticle(long articleId) {
        Article article = articleService.findArticleById(articleId);
        Map<String, Object> result = new HashMap<>();
        result.put("article", article);
        return result;
    }

    /***
     * 查看问题内容
     *
     * @param questionId 问题id
     * @return 问题信息
     */
    @RequestMapping("/viewQuestion")
    @ResponseBody
    public Map<String, Object> viewQuestion(long questionId) {
        Question question = questionService.findQuestionById(questionId);
        Map<String, Object> result = new HashMap<>();
        result.put("question", question);
        return result;
    }

    /***
     * 加载文章的评论
     *
     * @param articleId 文章id
     * @param offset    偏移量
     * @param max       一夜记录数
     * @return {total:总记录,remarkList:评论列表}
     */
    @RequestMapping("/listArticleRemark")
    @ResponseBody
    public Map<String, Object> listArticleRemark(long articleId, int offset, int max) {
        return articleService.listArticleRemark(articleId, offset, max);
    }

    /***
     * 加载问题评论
     *
     * @param questionId 问题id
     * @param offset     便宜量
     * @param max        一页记录数
     * @return {total:总记录,askList:评论列表}
     */
    @RequestMapping("/listQuestionRemark")
    @ResponseBody
    public Map<String, Object> listQuestionRemark(long questionId, int offset, int max) {
        return questionService.listQuestionRemark(questionId, offset, max);
    }


}
