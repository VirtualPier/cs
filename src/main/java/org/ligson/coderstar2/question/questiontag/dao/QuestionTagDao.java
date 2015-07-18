package org.ligson.coderstar2.question.questiontag.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionTag;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface QuestionTagDao extends BaseDao<QuestionTag> {
    public List<QuestionTag> findAllByQuestion(Question question);
}
