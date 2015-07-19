package org.ligson.coderstar2.article.service.impl;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.article.article.dao.ArticleDao;
import org.ligson.coderstar2.article.articletag.dao.ArticleTagDao;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.Remark;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.user.domains.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 * 文章服务
 */
public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao;
    private ArticleTagDao articleTagDao;

    public ArticleTagDao getArticleTagDao() {
        return articleTagDao;
    }

    public void setArticleTagDao(ArticleTagDao articleTagDao) {
        this.articleTagDao = articleTagDao;
    }

    public ArticleDao getArticleDao() {
        return articleDao;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

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

    @Override
    public Map<String, Object> list(int offset, int max) {
        Map<String, Object> result = new HashMap<>();
        int total = articleDao.countAll();
        List<Article> articles = articleDao.list(offset, max);
        result.put("total", total);
        result.put("rows", articles);
        return result;
    }

    @Override
    public List<SysTag> hotsTag(int limit) {
        return articleTagDao.listOrderArticle(limit);
    }

    @Override
    public List<List> hotAuthors(int day) {
        String preWeek = null;
        if (day > 0) {
            Date preWeekDate = DateUtils.subtractDay(new Date(), day);
            preWeek = DateUtils.format(preWeekDate, DateUtils.FORMAT_1);
        } else {
            preWeek = null;
        }
        return articleDao.countByArticleGroupByUser(preWeek);
    }

    @Override
    public Map<String, Object> findAllByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset) {
        List<Article> articles = articleDao.findAllByCategoryIdAndTagIdOrderBy(categoryId, tagId, order, max, offset);
        int total = articleDao.countByCategoryIdAndTagIdOrderBy(categoryId, tagId, order, max, offset);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("articleList", articles);
        return result;
    }
}
