package org.ligson.coderstar2.question.ask.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.question.domains.Question;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface AskDao extends BaseDao<Ask> {
    public List<Ask> findAllByQuestion(Question question);
}
