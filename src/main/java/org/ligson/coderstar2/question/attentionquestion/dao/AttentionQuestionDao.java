package org.ligson.coderstar2.question.attentionquestion.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.AttentionQuestion;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.user.domains.User;

/**
 * Created by ligson on 2015/7/16.
 */
public interface AttentionQuestionDao extends BaseDao<AttentionQuestion> {
    public int countByUserAndQuestion(User user, Question question);
}
