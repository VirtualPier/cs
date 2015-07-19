package org.ligson.coderstar2.system.category.service.impl;

import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.category.dao.CategoryDao;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2015/7/17.
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> list() {
        return categoryDao.list(0, 100);
    }

    @Override
    public List<Category> findQuestionCategoryList(Question question) {
        return categoryDao.findAllByQuestion(question);
    }

    @Override
    public List<List<Category>> findQuestionCategoryListByQuestionList(List<Question> questionList) {
        List<List<Category>> categoryList = new ArrayList<>();
        for (Question question : questionList) {
            categoryList.add(findQuestionCategoryList(question));
        }
        return categoryList;
    }
}
