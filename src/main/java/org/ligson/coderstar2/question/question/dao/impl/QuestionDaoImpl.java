package org.ligson.coderstar2.question.question.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.question.dao.QuestionDao;
import org.ligson.coderstar2.system.domains.Category;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements QuestionDao {
    @Override
    public List<Question> findByRightAskIsNullOrderBy(boolean hasDeal, String sort, int max, int offset) {
        StringBuilder sb = new StringBuilder("from Question q where q.state=" + Question.STATE_PUBLISH + " and ");
        if (hasDeal) {
            sb.append(" q.rightAsk is not null ");
        } else {
            sb.append(" q.rightAsk is null ");
        }
        sb.append(" order by ").append(sort).append(" desc ");
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setFirstResult(offset);
        query.setMaxResults(max);
        query.setCacheable(true);
        return (List<Question>) query.list();
    }

    @Override
    public int countByRightAskIsNullOrderBy(boolean hasDeal, String sort) {
        StringBuilder sb = new StringBuilder("select count(*) from Question q where q.state=" + Question.STATE_PUBLISH + " and ");
        if (hasDeal) {
            sb.append(" q.rightAsk is not null ");
        } else {
            sb.append(" q.rightAsk is null ");
        }
        sb.append(" order by ").append(sort).append(" desc ");
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setCacheable(true);
        Long tatal = (Long) query.uniqueResult();
        return tatal.intValue();
    }

    @Override
    public List<Question> findAllByStateOrderBy(int statePublish, String sort, String order, int offset, int max) {
        Query query = getCurrentSession().createQuery("from Question where state=:state  order by " + sort + " " + order);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        query.setInteger("state", statePublish);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        List<Question> questionList = (List<Question>) query.list();
        return questionList;
    }

    @Override
    public List<Question> findAllByRightAskIsNullAndMoneyGreaterThan(int money, String sort, String order, int max) {
        Query query = getCurrentSession().createQuery("from Question q where q.rightAsk is null and q.money>:m order by " + sort + " " + order);
        query.setDouble("m", money);
        query.setFirstResult(0);
        query.setMaxResults(max);
        List<Question> questionList = (List<Question>) query.list();
        return questionList;
    }

    @Override
    public List<Question> findAllByStateAndCategoryOrderBy(int statePublish, Category category, String sort, String order, int offset, int max) {
        Query query = getCurrentSession().createQuery("select distinct(qc.question) from QuestionCategory qc where qc.question.state=:state and qc.category.id=:categoryId order by qc.question." + sort + " " + order);
        query.setInteger("state", statePublish);
        query.setLong("categoryId", category.getId());
        query.setFirstResult(offset);
        query.setMaxResults(max);
        List<Question> questionList = (List<Question>) query.list();
        return questionList;
    }
}
