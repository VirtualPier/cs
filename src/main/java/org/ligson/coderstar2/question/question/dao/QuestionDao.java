package org.ligson.coderstar2.question.question.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.Question;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface QuestionDao extends BaseDao<Question> {
    public List<Question> findByRightAskIsNullOrderBy(boolean hasDeal, String sort, int max, int offset);

    public int countByRightAskIsNullOrderBy(boolean hasDeal, String sort);
}
