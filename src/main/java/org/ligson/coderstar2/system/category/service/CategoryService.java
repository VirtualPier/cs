package org.ligson.coderstar2.system.category.service;

import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.domains.Category;

import java.util.List;

/**
 * Created by ligson on 2015/7/17.
 */
public interface CategoryService {
    public List<Category> list();

    public List<Category> findQuestionCategoryList(Question question);
}
