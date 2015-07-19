package org.ligson.coderstar2.article.articlecategory.dao;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleCategory;
import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.system.domains.Category;

/**
 * Created by Ruby on 2015/7/16.
 */
public  interface ArticleCategoryDao extends BaseDao<ArticleCategory> {
    public ArticleCategory findByArticleAndCategory(Article article, Category category);
}
