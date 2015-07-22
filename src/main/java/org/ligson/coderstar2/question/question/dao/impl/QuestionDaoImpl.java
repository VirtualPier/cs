package org.ligson.coderstar2.question.question.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.question.dao.QuestionDao;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements QuestionDao {
    @Override
    public List<Question> findByRightAskIsNullAndCategoryIdOrderBy(boolean hasDeal, String sort, long categoryId, int max, int offset) {
        StringBuilder sb = null;
        if (categoryId >= 0) {
            sb = new StringBuilder("select distinct(qc.question) from QuestionCategory qc where qc.question.state=" + Question.STATE_PUBLISH + " and  qc.category.id=" + categoryId + " and ");
            if (hasDeal) {
                sb.append(" qc.question.rightAsk is not null ");
            } else {
                sb.append(" qc.question.rightAsk is null ");
            }
            sb.append(" order by qc.question.").append(sort).append(" desc ");
        } else {
            sb = new StringBuilder("from Question q where q.state=" + Question.STATE_PUBLISH + " and ");
            if (hasDeal) {
                sb.append(" q.rightAsk is not null ");
            } else {
                sb.append(" q.rightAsk is null ");
            }
            sb.append(" order by q.").append(sort).append(" desc ");
        }

        Query query = getCurrentSession().createQuery(sb.toString());
        query.setFirstResult(offset);
        query.setMaxResults(max);
        query.setCacheable(true);
        return (List<Question>) query.list();
    }

    @Override
    public int countByRightAskIsNullAndCategoryIdOrderBy(boolean hasDeal, String sort, long categoryId) {
        StringBuilder sb = null;
        if (categoryId >= 0) {
            sb = new StringBuilder("select count(qc.question) from QuestionCategory qc where qc.question.state=" + Question.STATE_PUBLISH + " and  qc.category.id=" + categoryId + " and ");
            if (hasDeal) {
                sb.append(" qc.question.rightAsk is not null ");
            } else {
                sb.append(" qc.question.rightAsk is null ");
            }
            sb.append(" order by ").append(sort).append(" desc ");
        } else {
            sb = new StringBuilder("select count(*) from Question q where q.state=" + Question.STATE_PUBLISH + " and ");
            if (hasDeal) {
                sb.append(" q.rightAsk is not null ");
            } else {
                sb.append(" q.rightAsk is null ");
            }
            sb.append(" order by ").append(sort).append(" desc ");
        }

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

    @Override
    public List<Question> findAllQuestionByCreatorAndState(User user, int statePublish, String sort, String order, int offset, int max) {
        Query query = null;
        if (statePublish >= 0) {
            query = getCurrentSession().createQuery("from Question q where q.creator.id=:userId and q.state=:state order by " + sort + " " + order);
            query.setLong("userId", user.getId());
            query.setInteger("state", statePublish);
        } else {
            query = getCurrentSession().createQuery("from Question q where q.creator.id=:userId  order by " + sort + " " + order);
            query.setLong("userId", user.getId());
        }
        query.setFirstResult(offset);
        query.setMaxResults(max);

        List<Question> questionList = (List<Question>) query.list();
        return questionList;
    }

    @Override
    public int countByUserAndState(User user, int statePublish) {
        Query query = getCurrentSession().createQuery("select count(*) from Question q where q.creator.id=:userId and q.state=:state");
        query.setLong("userId", user.getId());
        query.setInteger("state", statePublish);
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }

    @Override
    public List<Question> findAllByAttentionQuestion(User user, int offset, int max) {
        Query query = getCurrentSession().createQuery("select aq.question from AttentionQuestion aq where aq.user.id=:userId order by aq.createDate desc");
        query.setLong("userId", user.getId());
        query.setFirstResult(offset);
        query.setMaxResults(max);
        List<Question> questionList = (List<Question>) query.list();
        return questionList;
    }

    @Override
    public Map<String, Object> searchQuestion(String title, long tagId, long categoryId, long max, long offset, String sort, String order) {
        Query query = null;
        Query query2 = null;
        if (tagId >= 0 && categoryId >= 0) {
            query = getCurrentSession().createQuery("select a from Question a,QuestionTag at,QuestionCategory ac where a.id=at.question.id and a.id=ac.quesiton.id and a.title like :title order by a." + sort + " " + order);
            query2 = getCurrentSession().createQuery("select count(a) from Question a,QuestionTag at,ArticleCategory ac where a.id=at.question.id and a.id=ac.question.id and a.title like :title order by a." + sort + " " + order);
        } else if (tagId < 0 && categoryId >= 0) {
            query = getCurrentSession().createQuery("select a from Question a,QuestionCategory ac where a.id=ac.question.id  and a.title like :title order by a." + sort + " " + order);
            query2 = getCurrentSession().createQuery("select count(a) from Question a,QuestionCategory ac where a.id=ac.question.id  and a.title like :title order by a." + sort + " " + order);
        } else {
            query = getCurrentSession().createQuery("select a from Question a,QuestionTag at where a.id=at.question.id  and a.title like :title order by a." + sort + " " + order);
            query2 = getCurrentSession().createQuery("select count(a) from Question a,QuestionTag at where a.id=at.question.id  and a.title like :title order by a." + sort + " " + order);
        }
        query.setString("title", "%" + title + "%");
        query2.setString("title", "%" + title + "%");
        query.setFirstResult((int) offset);
        query.setMaxResults((int) max);
        List<Question> questionList = query.list();
        Map<String, Object> result = new HashMap<>();
        result.put("questionList", questionList);
        Long count = (Long) query2.uniqueResult();
        result.put("total", count.intValue());
        return result;
    }
}
