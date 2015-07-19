package org.ligson.coderstar2.question.questioncategory.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionCategory;
import org.ligson.coderstar2.system.domains.Category;

/**
 * Created by ligson on 2015/7/16.
 */
public interface QuestionCategoryDao extends BaseDao<QuestionCategory> {
    public QuestionCategory findByQuestionAndCategory(Question question, Category category);
}
