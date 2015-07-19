package org.ligson.coderstar2.system.systag.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.system.systag.dao.SysTagDao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class SysTagDaoImpl extends BaseDaoImpl<SysTag> implements SysTagDao {
    @Override
    public List<SysTag> findAllByQuestion(Question question) {
        Query query = getCurrentSession().createQuery("select qt.tag from QuestionTag qt where qt.question.id=:qId");
        query.setLong("qId", question.getId());
        List<SysTag> tags = (List<SysTag>) query.list();
        return tags;
    }

    @Override
    public List<List> findHotTag(int max) {
        List tagCount = new ArrayList<>();
        org.hibernate.SQLQuery query = getCurrentSession().createSQLQuery("select tag_id,count(tag_id) from (select ta.tag_id from article_tag ta union all select tq.tag_id from question_tag tq) taq group by tag_id order by count(tag_id) desc");
        query.setMaxResults(max);
        List list = query.list();
        for (Object object : list) {
            Object[] list1 = (Object[]) object;
            SysTag sysTag = getById(((BigInteger) list1[0]).longValue());

            List list2 = new ArrayList();
            list2.add(sysTag);
            list2.add(list1[1]);
            tagCount.add(list2);
        }
        return tagCount;
    }

    @Override
    public List<SysTag> findAllByArticle(Article article) {
        Query query = getCurrentSession().createQuery("select at.tag from ArticleTag at where at.article.id=:articleId ");
        query.setLong("articleId", article.getId());
        List<SysTag> sysTags = (List<SysTag>) query.list();
        return sysTags;
    }
}
