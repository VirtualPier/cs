package org.ligson.coderstar2.question.attentionquestion.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.attentionquestion.dao.AttentionQuestionDao;
import org.ligson.coderstar2.question.domains.AttentionQuestion;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.user.domains.User;

/**
 * Created by ligson on 2015/7/16.
 */
public class AttentionQuestionDaoImpl extends BaseDaoImpl<AttentionQuestion> implements AttentionQuestionDao {
    @Override
    public int countByUserAndQuestion(User user, Question question) {
        Query query = getCurrentSession().createQuery("select count(*) from AttentionQuestion aq where aq.question.id=:qId and aq.user.id=:userId");
        query.setLong("qId", question.getId());
        query.setLong("userId", user.getId());
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }
}
