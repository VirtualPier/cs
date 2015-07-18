package org.ligson.coderstar2.question.question.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.question.dao.QuestionDao;

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
        StringBuilder sb = new StringBuilder("select count(*) from Question q where ");
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
}
