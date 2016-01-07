package org.ligson.coderstar2.question.questioncategory.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionCategory;
import org.ligson.coderstar2.question.questioncategory.dao.QuestionCategoryDao;
import org.ligson.coderstar2.system.domains.Category;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class QuestionCategoryDaoImpl extends BaseDaoImpl<QuestionCategory> implements QuestionCategoryDao {
    @Override
    public QuestionCategory findByQuestionAndCategory(Question question, Category category) {
        Query query = getCurrentSession().createQuery("from QuestionCategory qc where qc.questionId=:qId and qc.categoryId=:cId");
        query.setParameter("qId",question.getId());
        query.setParameter("cId",category.getId());
        List<QuestionCategory> questionCategories = query.list();
        if (questionCategories.size() > 0) {
            return questionCategories.get(0);
        } else {
            return null;
        }
    }
}
