package org.ligson.coderstar2.question.question.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.ligson.coderstar2.article.domains.Article;
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
    private static Logger logger = Logger.getLogger(QuestionDaoImpl.class);

    @Override
    public List<Question> findByRightAskIsNullAndCategoryIdAndTagIdOrderBy(boolean hasDeal, String sort, long categoryId, long tagId, int max, int offset) {
        StringBuilder sb = new StringBuilder();
        if (categoryId >= 0 && tagId >= 0) {
            sb.append("select q from Question q,QuestionTag qt,QuestionCategory qc where q.id=qt.question.id and q.id=qc.question.id and q.state=0 qc.category.id=").append(categoryId);
        } else if (tagId < 0 && categoryId >= 0) {
            sb.append("select q from Question q,QuestionCategory qc where q.id=qc.question.id and q.state=0");
        } else {
            sb.append("select q from Question q,QuestionTag qt where q.id=qt.question.id  and q.state=0 ");
        }
        if (hasDeal) {
            sb.append(" and q.rightAsk is not null ");
        } else {
            sb.append(" and q.rightAsk is null ");
        }
        sb.append(" order by q.").append(sort).append(" desc ");
        String hql = sb.toString();
        logger.debug(hql);
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        query.setCacheable(true);
        return (List<Question>) query.list();
    }

    @Override
    public int countByRightAskIsNullAndCategoryIdAndTagIdOrderBy(boolean hasDeal, String sort, long categoryId, long tagId) {
        StringBuilder sb = new StringBuilder();
        if (categoryId >= 0 && tagId >= 0) {
            sb.append("select count(q) from Question q,QuestionTag qt,QuestionCategory qc where q.id=qt.question.id and q.id=qc.question.id and q.state=0 qc.category.id=").append(categoryId);
        } else if (tagId < 0 && categoryId >= 0) {
            sb.append("select count(q) from Question q,QuestionCategory qc where q.id=qc.question.id and q.state=0");
        } else {
            sb.append("select count(q) from Question q,QuestionTag qt where q.id=qt.question.id  and q.state=0 ");
        }
        if (hasDeal) {
            sb.append(" and q.rightAsk is not null ");
        } else {
            sb.append(" and q.rightAsk is null ");
        }
        sb.append(" order by q.").append(sort).append(" desc ");
        String hql = sb.toString();
        logger.debug(hql);
        Query query = getCurrentSession().createQuery(hql);
        Long total = (Long) query.uniqueResult();
        return total.intValue();
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

    @Override
    public void execuRemoveSql(long[] ids) {
        StringBuilder idMgr = new StringBuilder();
        boolean flag = false;
        for (long id : ids) {
            if (flag) {
                idMgr.append(",");
            } else {
                flag = true;
            }
            idMgr.append(id);
        }
        getCurrentSession().createSQLQuery(findRemoveSql(idMgr.toString(), "question_category")).executeUpdate();
        getCurrentSession().createSQLQuery(findRemoveSql(idMgr.toString(), "question_tag")).executeUpdate();
        getCurrentSession().createSQLQuery("DELETE FROM question WHERE id in (" + idMgr.toString() + ")").executeUpdate();
    }

    @Override
    public int countByCreatorAndStateAndTitleLike(User user, int statePublish, String title) {
        Query query = null;
        if (statePublish >= 0) {
            String hql = "select count(*) from Question q where q.creator.id=:userId and q.state=:state ";
            if (StringUtils.isNotBlank(title)) {
                hql += " and q.title like :title";
            }
            query = getCurrentSession().createQuery(hql);
            query.setLong("userId", user.getId());
            query.setInteger("state", statePublish);
            if (StringUtils.isNotBlank(title)) {
                query.setString("title", "%" + title + "%");
            }
        } else {
            String hql = "select count(*) from Question q where q.creator.id=:userId ";
            if (StringUtils.isNotBlank(title)) {
                hql += " and q.title like :title";
            }
            query = getCurrentSession().createQuery(hql);
            query.setLong("userId", user.getId());
            if (StringUtils.isNotBlank(title)) {
                query.setString("title", "%" + title + "%");
            }
        }
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }

    @Override
    public List<Question> findAllQuestionByUserAndTitleLikeOrder(User user, String title, int offset, int max, String sort, String order) {
        String hql = "from Question q where q.creator.id=:userId ";
        if (StringUtils.isNotBlank(title)) {
            hql += " and q.title like :title";
        }
        hql += " order by q." + sort + " " + order;
        Query query = getCurrentSession().createQuery(hql);
        query.setLong("userId", user.getId());
        if (StringUtils.isNotBlank(title)) {
            query.setString("title", "%" + title + "%");
        }
        query.setFirstResult(offset);
        query.setMaxResults(max);
        List<Question> questionList = (List<Question>) query.list();
        return questionList;
    }


    /**
     * 组装需要删除的语句
     *
     * @param ids
     * @param tableName
     * @return
     */
    private String findRemoveSql(String ids, String tableName) {
        return "DELETE FROM " + tableName + " WHERE question_id in (" + ids + ");";
    }
}
