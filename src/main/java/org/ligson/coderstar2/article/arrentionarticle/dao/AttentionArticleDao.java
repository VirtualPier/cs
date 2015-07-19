package org.ligson.coderstar2.article.arrentionarticle.dao;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.AttentionArticle;
import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.user.domains.User;

/**
 * Created by Ruby on 2015/7/16.
 */
public interface AttentionArticleDao extends BaseDao<AttentionArticle> {
    public AttentionArticle findByUserAndArticle(User user, Article article);
}
