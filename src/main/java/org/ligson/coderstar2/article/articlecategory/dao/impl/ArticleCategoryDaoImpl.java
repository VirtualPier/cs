package org.ligson.coderstar2.article.articlecategory.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.ligson.coderstar2.article.articlecategory.dao.ArticleCategoryDao;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleCategory;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.system.domains.Category;

import java.util.List;

/**
 * Created by Ruby on 2015/7/16.
 */
public class ArticleCategoryDaoImpl extends BaseDaoImpl<ArticleCategory> implements ArticleCategoryDao {
    @Override
    public ArticleCategory findByArticleAndCategory(Article article, Category category) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from ArticleCategory ac where ac.articleId=:aId and ac.categoryId=:cId");
        query.setLong("aId", article.getId());
        query.setLong("cId", category.getId());
        List<ArticleCategory> articleCategories = query.list();
        if (articleCategories.size() > 0) {
            return articleCategories.get(0);
        } else {
            return null;
        }
    }
}
