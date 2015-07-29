package org.ligson.coderstar2.system.category.service;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleCategory;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionCategory;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/17.
 */
public interface CategoryService {
    public List<Category> list();

    public List<Category> findQuestionCategoryList(Question question);

    public List<List<Category>> findQuestionCategoryListByQuestionList(List<Question> questionList);

    public ArticleCategory addArticleToCategory(Article article, long categoryId);

    public QuestionCategory addQuestionToCategory(Question question, long categoryId);

    public Category findCategoryById(long categoryId);

    public List<Category> findArticleCategoryList(Article article);

    public List<Category> list(int max, int offset);

    public Map<String, Object> addCategory(String name, String description, int sortIndex);

    public Map<String, Object> modifyCategory(long id, String name, String description, int sortIndex,CommonsMultipartFile poster);

    public List<Map<String, Object>> deleteCategoryList(User user, long[] idArray);

    public List<Question> listQuestionByCategory(Category category);

    public List<Article> listArticleByCategory(Category category);
}
