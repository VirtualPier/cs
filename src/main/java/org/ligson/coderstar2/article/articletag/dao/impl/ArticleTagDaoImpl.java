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
    public List<SysTag> listOrderArticle(int limit) {
        Query query = getCurrentSession().createQuery("select ta.tag from ArticleTag ta group by ta.tag order by count(ta.article) desc");
        query.setFirstResult(0);
        query.setMaxResults(limit);
        List<SysTag> sysTags = (List<SysTag>) query.list();
        return sysTags;
    }

    @Override
    public List<SysTag> findAllByArticle(Article article) {
        Query query = getCurrentSession().createQuery("select at.tag from ArticleTag at where at.article.id=:articleId");
        query.setLong("articleId", article.getId());
        List<SysTag> list = (List<SysTag>) query.list();
        return list;
    }
}
