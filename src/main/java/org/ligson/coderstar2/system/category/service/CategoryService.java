package org.ligson.coderstar2.system.category.service;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleCategory;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionCategory;
import org.ligson.coderstar2.system.domains.Category;

import java.util.List;

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
}
