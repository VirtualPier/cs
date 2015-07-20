package org.ligson.coderstar2.question.question.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface QuestionDao extends BaseDao<Question> {
    public List<Question> findByRightAskIsNullOrderBy(boolean hasDeal, String sort, int max, int offset);

    public int countByRightAskIsNullOrderBy(boolean hasDeal, String sort);

    public List<Question> findAllByStateOrderBy(int statePublish, String sort, String order, int offset, int max);

    public List<Question> findAllByRightAskIsNullAndMoneyGreaterThan(int money, String sort, String order, int max);

    public List<Question> findAllByStateAndCategoryOrderBy(int statePublish, Category category, String sort, String order, int offset, int max);

    public List<Question> findAllQuestionByCreatorAndState(User user, int statePublish, String sort, String order, int offset, int max);

    public int countByUserAndState(User user, int statePublish);

    public List<Question> findAllByAttentionQuestion(User user, int offset, int max);
}
