package org.ligson.coderstar2.question.attentionquestion.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.attentionquestion.dao.AttentionQuestionDao;
import org.ligson.coderstar2.question.domains.AttentionQuestion;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class AttentionQuestionDaoImpl extends BaseDaoImpl<AttentionQuestion> implements AttentionQuestionDao {
    @Override
    public int countByUserAndQuestion(User user, Question question) {
        Query query = getCurrentSession().createQuery("select count(aq.id) from AttentionQuestion aq where aq.questionId=:qId and aq.userId=:userId");
        query.setLong("qId", question.getId());
        query.setLong("userId", user.getId());
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }

    @Override
    public AttentionQuestion findByUserAndQuestion(User user, Question question) {
        Query query = getCurrentSession().createQuery("from AttentionQuestion aq where aq.user.id=:userId and aq.question.id=:questionId");
        query.setLong("userId", user.getId());
        query.setLong("questionId", question.getId());
        List<AttentionQuestion> attentionQuestions = (List<AttentionQuestion>) query.list();
        if (attentionQuestions.size() > 0) {
            return attentionQuestions.get(0);
        } else {
            return null;
        }
    }
}
