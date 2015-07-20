package org.ligson.coderstar2.article.articletag.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.article.articletag.dao.ArticleTagDao;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleTag;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.system.domains.SysTag;

import java.util.List;

/**
 * Created by Ruby on 2015/7/16.
 */
public class ArticleTagDaoImpl extends BaseDaoImpl<ArticleTag> implements ArticleTagDao {

    @Override
    public List<ArticleTag> findAllByArticle(Article article) {
        return null;
    }
}
