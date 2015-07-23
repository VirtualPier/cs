package org.ligson.coderstar2.question.question.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
public interface QuestionDao extends BaseDao<Question> {
    public List<Question> findByRightAskIsNullAndCategoryIdOrderBy(boolean hasDeal, String sort, long categoryId, int max, int offset);

    public int countByRightAskIsNullAndCategoryIdOrderBy(boolean hasDeal, String sort,long categoryId);

    public List<Question> findAllByStateOrderBy(int statePublish, String sort, String order, int offset, int max);

    public List<Question> findAllByRightAskIsNullAndMoneyGreaterThan(int money, String sort, String order, int max);

    public List<Question> findAllByStateAndCategoryOrderBy(int statePublish, Category category, String sort, String order, int offset, int max);

    public List<Question> findAllQuestionByCreatorAndState(User user, int statePublish, String sort, String order, int offset, int max);

    public int countByUserAndState(User user, int statePublish);

    public List<Question> findAllByAttentionQuestion(User user, int offset, int max);

    public Map<String,Object> searchQuestion(String title, long tagId, long categoryId, long max, long offset, String sort, String order);

    void execuRemoveSql(long[] ids);
}
