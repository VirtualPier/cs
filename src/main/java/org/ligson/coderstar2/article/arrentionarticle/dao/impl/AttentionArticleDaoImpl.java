package org.ligson.coderstar2.article.arrentionarticle.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.article.arrentionarticle.dao.AttentionArticleDao;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.AttentionArticle;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by Ruby on 2015/7/16.
 */
public class AttentionArticleDaoImpl extends BaseDaoImpl<AttentionArticle> implements AttentionArticleDao {
    @Override
    public AttentionArticle findByUserAndArticle(User user, Article article) {
        Query query = getCurrentSession().createQuery("from AttentionArticle aa where aa.user.id=:uId and aa.article.id=:aId");
        query.setLong("uId", user.getId());
        query.setLong("aId", article.getId());
        List<AttentionArticle> attentionArticles = query.list();
        if (attentionArticles.size() > 0) {
            return attentionArticles.get(0);
        } else {
            return null;
        }
    }
}
