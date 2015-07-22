package org.ligson.coderstar2.question.questiontag.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionTag;
import org.ligson.coderstar2.question.questioncategory.dao.QuestionCategoryDao;
import org.ligson.coderstar2.question.questiontag.dao.QuestionTagDao;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class QuestionTagDaoImpl extends BaseDaoImpl<QuestionTag> implements QuestionTagDao {
    @Override
    public List<QuestionTag> findAllByQuestion(Question question) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from QuestionTag qt where qt.question.id=:qid");
        query.setLong("qid", question.getId());
        List<QuestionTag> questionTags = (List<QuestionTag>) query.list();
        return questionTags;
    }
}
