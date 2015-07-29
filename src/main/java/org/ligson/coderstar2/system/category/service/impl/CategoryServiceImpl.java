package org.ligson.coderstar2.system.category.service.impl;

import com.boful.common.file.utils.FileType;
import com.boful.common.file.utils.FileUtils;
import org.ligson.coderstar2.article.articlecategory.dao.ArticleCategoryDao;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleCategory;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionCategory;
import org.ligson.coderstar2.question.questioncategory.dao.QuestionCategoryDao;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.category.dao.CategoryDao;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.conf.utils.Bootstrap;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by ligson on 2015/7/17.
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;
    private ArticleCategoryDao articleCategoryDao;
    private QuestionCategoryDao questionCategoryDao;
    private QuestionService questionService;
    private ArticleService articleService;

    public QuestionService getQuestionService() {
        return questionService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

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

    @Override
    public List<Category> findArticleCategoryList(Article article) {
        return categoryDao.findAllByArticle(article);
    }

    @Override
    public List<Category> list(int max, int offset) {
        return categoryDao.list(offset, max);
    }

    @Override
    public Map<String, Object> addCategory(String name, String description, int sortIndex) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        category.setSortIndex(sortIndex);
        categoryDao.saveOrUpdate(category);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @Override
    public Map<String, Object> modifyCategory(long id, String name, String description, int sortIndex, CommonsMultipartFile poster){
        Category category = categoryDao.getById(id);
        if (poster != null) {
            if (FileType.isImage(poster.getOriginalFilename())) {
                String fileType = FileUtils.getFileSufix(poster.getName());
                String url = "/upload/category/" + category.getId() + "/" + UUID.randomUUID().toString() + "." + fileType;
                File file = new File(Bootstrap.webRoot, url);
                if (!file.getParentFile().exists()) {
                    file.mkdirs();
                }

                if (category.getPoster() != null) {
                    File old = new File(Bootstrap.webRoot, category.getPoster());
                    if (old.exists()) {
                        old.delete();
                    }
                }
                try {
                    poster.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                category.setPoster(url);
            }
        }
        category.setName(name);
        category.setDescription(description);
        category.setSortIndex(sortIndex);
        categoryDao.saveOrUpdate(category);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("category", category);
        return result;
    }


    public Map<String, Object> deleteCategory(User user, long categoryId) {
        Map<String, Object> result = new HashMap<>();
        Category category = categoryDao.getById(categoryId);
        List<Question> questionList = listQuestionByCategory(category);
        long[] qIds = new long[questionList.size()];
        for (int i = 0; i < questionList.size(); i++) {
            qIds[i] = questionList.get(i).getId();
        }
        questionService.deleteQuestion(qIds);

        List<Article> articleList = listArticleByCategory(category);
        long[] aIds = new long[articleList.size()];
        for (int i = 0; i < articleList.size(); i++) {
            aIds[i] = articleList.get(i).getId();
        }
        articleService.deleteArticles(user, aIds);

        if (category != null) {
            categoryDao.delete(category);
        }

        result.put("success", true);
        return result;
    }

    @Override
    public List<Map<String, Object>> deleteCategoryList(User user, long[] idArray) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (long id : idArray) {
            Map<String, Object> tmp = deleteCategory(user, id);
            result.add(tmp);
        }
        return result;
    }

    @Override
    public List<Question> listQuestionByCategory(Category category) {
        return categoryDao.listQuestionByCategory(category);
    }

    @Override
    public List<Article> listArticleByCategory(Category category) {
        return categoryDao.listArticleByCategory(category);
    }
}
