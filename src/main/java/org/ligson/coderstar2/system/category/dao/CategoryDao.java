package org.ligson.coderstar2.system.category.dao;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.domains.Category;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface CategoryDao extends BaseDao<Category> {
    public List<Category> findAllByQuestion(Question question);

    public List<Category> findAllByArticle(Article article);

    public List<Article> listArticleByCategory(Category category);

    public List<Question> listQuestionByCategory(Category category);
}
