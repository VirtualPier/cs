package org.ligson.coderstar2.system.category.service.impl;

import org.ligson.coderstar2.article.articlecategory.dao.ArticleCategoryDao;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleCategory;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionCategory;
import org.ligson.coderstar2.question.questioncategory.dao.QuestionCategoryDao;
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
    private ArticleCategoryDao articleCategoryDao;
    private QuestionCategoryDao questionCategoryDao;

    public QuestionCategoryDao getQuestionCategoryDao() {
        return questionCategoryDao;
    }

    public void setQuestionCategoryDao(QuestionCategoryDao questionCategoryDao) {
        this.questionCategoryDao = questionCategoryDao;
    }

    public ArticleCategoryDao getArticleCategoryDao() {
        return articleCategoryDao;
    }

    public void setArticleCategoryDao(ArticleCategoryDao articleCategoryDao) {
        this.articleCategoryDao = articleCategoryDao;
    }

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

    @Override
    public ArticleCategory addArticleToCategory(Article article, long categoryId) {
        Category category = findCategoryById(categoryId);
        ArticleCategory articleCategory = articleCategoryDao.findByArticleAndCategory(article, category);
        if (articleCategory == null) {
            articleCategory = new ArticleCategory();
            articleCategory.setArticle(article);
            articleCategory.setCategory(category);
            articleCategoryDao.saveOrUpdate(articleCategory);
            category.setArticleNum(category.getArticleNum() + 1);
            categoryDao.saveOrUpdate(category);
        }
        return articleCategory;
    }

    @Override
    public QuestionCategory addQuestionToCategory(Question question, long categoryId) {
        Category category = findCategoryById(categoryId);
        QuestionCategory questionCategory = questionCategoryDao.findByQuestionAndCategory(question, category);
        if (questionCategory == null) {
            questionCategory = new QuestionCategory();
            questionCategory.setQuestion(question);
            questionCategory.setCategory(category);
            questionCategoryDao.saveOrUpdate(questionCategory);
            category.setArticleNum(category.getArticleNum() + 1);
            categoryDao.saveOrUpdate(category);
        }
        return questionCategory;
    }

    @Override
    public Category findCategoryById(long categoryId) {
        return categoryDao.getById(categoryId);
    }
}
