package org.ligson.coderstar2.article.articlerate.dao;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleRate;
import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.user.domains.User;

/**
 * Created by Ruby on 2015/7/16.
 */
public interface ArticleRateDao extends BaseDao<ArticleRate> {
    public ArticleRate findByUserAndArticle(User currentUser, Article article);
}
