package org.ligson.coderstar2.system.cache;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.pay.domains.Withdraw;
import org.ligson.coderstar2.pay.service.PayService;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/12/25.
 * 系统缓存
 */

public class SysCache {
    private QuestionService questionService;
    private ArticleService articleService;
    private CategoryService categoryService;
    private UserService userService;
    private PayService payService;

    private List<Question> newestQuestionList;
    private List<Article> newestArticleList;
    private List<Category> categoryList;
    private List<Question> recommendQuestionList;
    private List<Article> recommendArticleList;
    private List<Question> hotQuestions;
    private List<Question> waitQuestionList;
    private List<Question> offerQuestionList;
    private List<Article> hotArticles;
    private List<User> hotAuthors;
    private List<User> hotReplyers;
    private List<Withdraw> withdrawList;

    public void init() {
        newestQuestionList = questionService.newestQuestion(5);
        newestArticleList = articleService.newestArticle(5);
        categoryList = categoryService.list();
        recommendQuestionList = questionService.questionListOrderBy(0, 3, "recommendNum", "desc");
        recommendArticleList = articleService.articleListOrderBy(0, 3, "recommendNum", "desc");
        hotQuestions = questionService.findHotQuestion(20);
        Map<String, Object> result = questionService.searchQuestion(-1, -1, false, "createDate", 20, 0);
        waitQuestionList = (List<Question>) result.get("questionList");
        offerQuestionList = questionService.findOfferQuesiton(20);
        hotArticles = articleService.findHotArticle(20);
        hotAuthors = userService.hotAuthors(5);
        hotReplyers = userService.hotReplyers(5);
        withdrawList = payService.newestWithdraw(5);
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

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public PayService getPayService() {
        return payService;
    }

    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    public List<Question> getNewestQuestionList() {
        return newestQuestionList;
    }

    public void setNewestQuestionList(List<Question> newestQuestionList) {
        this.newestQuestionList = newestQuestionList;
    }

    public List<Article> getNewestArticleList() {
        return newestArticleList;
    }

    public void setNewestArticleList(List<Article> newestArticleList) {
        this.newestArticleList = newestArticleList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Question> getRecommendQuestionList() {
        return recommendQuestionList;
    }

    public void setRecommendQuestionList(List<Question> recommendQuestionList) {
        this.recommendQuestionList = recommendQuestionList;
    }

    public List<Article> getRecommendArticleList() {
        return recommendArticleList;
    }

    public void setRecommendArticleList(List<Article> recommendArticleList) {
        this.recommendArticleList = recommendArticleList;
    }

    public List<Question> getHotQuestions() {
        return hotQuestions;
    }

    public void setHotQuestions(List<Question> hotQuestions) {
        this.hotQuestions = hotQuestions;
    }

    public List<Question> getWaitQuestionList() {
        return waitQuestionList;
    }

    public void setWaitQuestionList(List<Question> waitQuestionList) {
        this.waitQuestionList = waitQuestionList;
    }

    public List<Question> getOfferQuestionList() {
        return offerQuestionList;
    }

    public void setOfferQuestionList(List<Question> offerQuestionList) {
        this.offerQuestionList = offerQuestionList;
    }

    public List<Article> getHotArticles() {
        return hotArticles;
    }

    public void setHotArticles(List<Article> hotArticles) {
        this.hotArticles = hotArticles;
    }

    public List<User> getHotAuthors() {
        return hotAuthors;
    }

    public void setHotAuthors(List<User> hotAuthors) {
        this.hotAuthors = hotAuthors;
    }

    public List<User> getHotReplyers() {
        return hotReplyers;
    }

    public void setHotReplyers(List<User> hotReplyers) {
        this.hotReplyers = hotReplyers;
    }

    public List<Withdraw> getWithdrawList() {
        return withdrawList;
    }

    public void setWithdrawList(List<Withdraw> withdrawList) {
        this.withdrawList = withdrawList;
    }

}
