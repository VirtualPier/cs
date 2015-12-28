package org.ligson.coderstar2.system.cache;

import org.apache.commons.collections.CollectionUtils;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.pay.domains.Withdraw;
import org.ligson.coderstar2.pay.service.PayService;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.redis.utils.RedisClient;
import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/12/25.
 * 系统缓存
 */
@Component(value = "sysCache")
public class SysCache {
    @Resource
    private QuestionService questionService;
    @Resource
    private ArticleService articleService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private UserService userService;
    @Resource
    private PayService payService;
    @Resource
    private RedisClient redisClient;

    public void init() {
        cacheNewestQuestionList();
        cacheNewestArticleList();
        cacheCategoryList();
        cacheRecommendQuestionList();
        cacheRecommendArticleList();
        cacheHotQuestions();
        cacheHotArticles();
        cacheWaitQuestionList();

        cacheHotAuthors();
        cacheHotReplyers();

        cacheOfferQuestionList();
        cacheWithdrawList();

    }

    public void cacheNewestQuestionList() {
        List<Question> newestQuestionList = questionService.newestQuestion(5);
        redisClient.set("newestQuestionList", newestQuestionList);
    }

    public List<Question> getNewestQuestionList() {
        Class<List> clazz = List.class;
        List list = redisClient.get("newestQuestionList", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheNewestQuestionList();
        }
        return (List<Question>) list;
    }


    public void cacheNewestArticleList() {
        List<Article> newestArticleList = articleService.newestArticle(5);
        redisClient.set("newestArticleList", newestArticleList);
    }

    public List<Article> getNewestArticleList() {
        Class<List> clazz = List.class;
        List list = redisClient.get("newestArticleList", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheNewestQuestionList();
        }
        return (List<Article>) list;
    }


    public void cacheCategoryList() {
        List<Category> categoryList = categoryService.list();
        redisClient.set("categoryList", categoryList);
    }

    public List<Category> getCategoryList() {
        Class<List> clazz = List.class;
        List list = redisClient.get("categoryList", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheCategoryList();
        }
        return (List<Category>) list;
    }

    public void cacheRecommendQuestionList() {
        List<Question> recommendQuestionList = questionService.questionListOrderBy(0, 3, "recommendNum", "desc");
        redisClient.set("recommendQuestionList", recommendQuestionList);
    }


    public List<Question> getRecommendQuestionList() {
        Class<List> clazz = List.class;
        List list = redisClient.get("recommendQuestionList", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheRecommendQuestionList();
        }
        return (List<Question>) list;
    }

    public void cacheRecommendArticleList() {
        List<Article> recommendArticleList = articleService.articleListOrderBy(0, 3, "recommendNum", "desc");
        redisClient.set("recommendArticleList", recommendArticleList);
    }


    public List<Article> getRecommendArticleList() {
        Class<List> clazz = List.class;
        List list = redisClient.get("recommendArticleList", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheRecommendArticleList();
        }
        return (List<Article>) list;
    }

    public void cacheHotQuestions() {
        List<Question> hotQuestions = questionService.findHotQuestion(20);
        redisClient.set("hotQuestions", hotQuestions);
    }

    public List<Question> getHotQuestions() {
        Class<List> clazz = List.class;
        List list = redisClient.get("hotQuestions", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheHotQuestions();
        }
        return (List<Question>) list;
    }

    public void cacheWaitQuestionList() {
        Map<String, Object> result = questionService.searchQuestion(-1, -1, false, "createDate", 20, 0);
        List<Question> waitQuestionList = (List<Question>) result.get("questionList");
        redisClient.set("waitQuestionList", waitQuestionList);
    }

    public List<Question> getWaitQuestionList() {
        Class<List> clazz = List.class;
        List list = redisClient.get("waitQuestionList", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheWaitQuestionList();
        }
        return (List<Question>) list;
    }


    public void cacheOfferQuestionList() {
        List<Question> offerQuestionList = questionService.findOfferQuesiton(20);
        redisClient.set("offerQuestionList", offerQuestionList);
    }

    public List<Question> getOfferQuestionList() {
        Class<List> clazz = List.class;
        List list = redisClient.get("offerQuestionList", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheOfferQuestionList();
        }
        return (List<Question>) list;
    }


    public void cacheHotArticles() {
        List<Article> hotArticles = articleService.findHotArticle(20);
        redisClient.set("hotArticles", hotArticles);
    }

    public List<Article> getHotArticles() {
        Class<List> clazz = List.class;
        List list = redisClient.get("hotArticles", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheHotArticles();
        }
        return (List<Article>) list;
    }

    public void cacheHotAuthors() {
        List<User> hotAuthors = userService.hotAuthors(5);
        redisClient.set("hotAuthors", hotAuthors);
    }

    public List<User> getHotAuthors() {
        Class<List> clazz = List.class;
        List list = redisClient.get("hotAuthors", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheHotAuthors();
        }
        return (List<User>) list;
    }

    public void cacheHotReplyers() {
        List<User> hotReplyers = userService.hotReplyers(5);
        redisClient.set("hotReplyers", hotReplyers);
    }

    public List<User> getHotReplyers() {
        Class<List> clazz = List.class;
        List list = redisClient.get("hotReplyers", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheHotReplyers();
        }
        return (List<User>) list;
    }

    public void cacheWithdrawList() {
        List<Withdraw> withdrawList = payService.newestWithdraw(5);
        redisClient.set("withdrawList", withdrawList);
    }

    public List<Withdraw> getWithdrawList() {
        Class<List> clazz = List.class;
        List list = redisClient.get("withdrawList", clazz);
        if (CollectionUtils.isEmpty(list)) {
            cacheWithdrawList();
        }
        return (List<Withdraw>) list;
    }


}
