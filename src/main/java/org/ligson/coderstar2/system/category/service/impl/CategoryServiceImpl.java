package org.ligson.coderstar2.system.category.service.impl;

import org.ligson.coderstar2.system.category.dao.CategoryDao;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;

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
}
