package org.ligson.coderstar2.system.systag.dao.impl;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.system.systag.dao.SysTagDao;
import org.ligson.coderstar2.user.domains.User;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class SysTagDaoImpl extends BaseDaoImpl<SysTag> implements SysTagDao {
    @Override
    public List<SysTag> findAllByQuestion(Question question) {
        Query query = getCurrentSession().createQuery("select distinct(st) from QuestionTag qt,SysTag st where qt.questionId=:qId and st.id=qt.tagId");
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
        Query query = getCurrentSession().createQuery("select distinct(st) from ArticleTag at,SysTag st where at.articleId=:articleId and st.id=at.tagId");
        query.setLong("articleId", article.getId());
        List<SysTag> sysTags = query.list();
        return sysTags;
    }

    @Override
    public List<SysTag> findAllByCreator(User user, int max) {
        Query query = getCurrentSession().createQuery("from SysTag st where st.creatorId=:userId");
        query.setMaxResults(max);
        query.setLong("userId", user.getId());
        List<SysTag> sysTags = (List<SysTag>) query.list();
        return sysTags;
    }

    @Override
    public List<SysTag> listOrderArticle(int limit) {
        Query query = getCurrentSession().createQuery("select ta.tagId from ArticleTag ta group by ta.tagId order by count(ta.articleId) desc");
        query.setFirstResult(0);
        query.setMaxResults(limit);
        List<Long> idList = (List<Long>) query.list();
        List<SysTag> sysTags = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(idList)) {
            query = getCurrentSession().createQuery("from SysTag st where st.id in (:idList)");
            query.setParameterList("idList", idList);
            sysTags = query.list();
        }
        return sysTags;
    }

    @Override
    public List<SysTag> findQuestionHotTags(int max) {
        Query query = getCurrentSession().createQuery("select qt.tagId from QuestionTag qt group by qt.tagId order by count(qt.questionId) desc");
        query.setFirstResult(0);
        query.setMaxResults(max);
        List<Long> idList = query.list();
        query = getCurrentSession().createQuery("from SysTag st where st.id in (:idList)");
        query.setParameterList("idList", idList);
        List<SysTag> sysTags = (List<SysTag>) query.list();
        return sysTags;
    }

}
