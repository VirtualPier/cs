package org.ligson.coderstar2.question.ask.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.ask.dao.AskDao;
import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionTag;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class AskDaoImpl extends BaseDaoImpl<Ask> implements AskDao {
    @Override
    public List<Ask> findAllByQuestion(Question question) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Ask a where a.question.id=:qid");
        query.setLong("qid", question.getId());
        List<Ask> asks = (List<Ask>)query.list();
        return asks;
    }
}
