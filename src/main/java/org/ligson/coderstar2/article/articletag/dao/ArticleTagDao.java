package org.ligson.coderstar2.article.articletag.dao;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleTag;
import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.system.domains.SysTag;

import java.util.List;

/**
 * Created by Ruby on 2015/7/16.
 */
public interface ArticleTagDao extends BaseDao<ArticleTag> {

    public List<ArticleTag> findAllByArticle(Article article);
}
