package org.ligson.coderstar2.article.service.impl;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.Remark;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 * 文章服务
 */
public class ArticleServiceImpl implements ArticleService {
    @Override
    public Map attentionArticle(User user, Article article) {
        return null;
    }

    @Override
    public Map auditArticle(User user, Article article) {
        return null;
    }

    @Override
    public Map createArticle(String title, String content, User creator, String[] tags, Long[] categroyIds) {
        return null;
    }

    @Override
    public Map deleteArticle(User user, long articleId) {
        return null;
    }

    @Override
    public List<Map<String, Object>> deleteArticles(User user, long[] articleIds) {
        return null;
    }

    @Override
    public Map modifyArticle(User user, Article article, String title, String content, String[] tags, List<Long> categoryIds) {
        return null;
    }

    @Override
    public Map removeAttention(User user, Article article) {
        return null;
    }

    @Override
    public Map rewardArticle(User currentUser, Article article, double money) {
        return null;
    }

    @Override
    public Map saveRemark(User creator, Article article, String comment) {
        return null;
    }

    @Override
    public Map searchArticle(String title, String description, String tagName, Category category, int max, int offset, String sort, String orderr) {
        return null;
    }

    @Override
    public Map searchMyArticle(User currentUser, int offset, int max, String sort, String orderr) {
        return null;
    }

    @Override
    public Map supportArticle(User currentUser, Article article, boolean isSupport) {
        return null;
    }

    @Override
    public Map supportRemark(User currentUser, Remark remark, boolean isSupport) {
        return null;
    }
}
