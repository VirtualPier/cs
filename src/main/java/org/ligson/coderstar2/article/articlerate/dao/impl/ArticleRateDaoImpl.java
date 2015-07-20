package org.ligson.coderstar2.article.articlerate.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.article.articlerate.dao.ArticleRateDao;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleRate;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by Ruby on 2015/7/16.
 */
public class ArticleRateDaoImpl extends BaseDaoImpl<ArticleRate> implements ArticleRateDao {
    @Override
    public ArticleRate findByUserAndArticle(User currentUser, Article article) {
        Query query = getCurrentSession().createQuery("from ArticleRate ar where ar.user.id=:uId and ar.article.id=:aId");
        query.setLong("uId", currentUser.getId());
        query.setLong("aId", article.getId());
        List<ArticleRate> articles = query.list();
        if (articles.size() > 0) {
            return articles.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int countByArticleAndIsSupport(Article article, boolean isSupport) {
        Query query = getCurrentSession().createQuery("select count(*) from ArticleRate ar where ar.article.id=:aId and ar.support=:isSupport");
        query.setLong("aId", article.getId());
        query.setBoolean("isSupport", isSupport);
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }
}
