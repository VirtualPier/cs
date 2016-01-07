package org.ligson.coderstar2.system.category.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.category.dao.CategoryDao;
import org.ligson.coderstar2.system.domains.Category;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {
    @Override
    public List<Category> findAllByQuestion(Question question) {
        Session session = getCurrentSession();
        String hql = "select c from QuestionCategory qc,Category c where qc.questionId=:qId and c.id=qc.categoryId";
        Query query = session.createQuery(hql);
        query.setLong("qId", question.getId());
        List<Category> categories = (List<Category>) query.list();
        return categories;
    }

    @Override
    public List<Category> findAllByArticle(Article article) {
        Session session = getCurrentSession();
        String hql = "select c from ArticleCategory ac,Category c where ac.articleId=:aId and c.id=ac.categoryId";
        Query query = session.createQuery(hql);
        query.setLong("aId", article.getId());
        List<Category> categories = (List<Category>) query.list();
        return categories;
    }

    @Override
    public List<Article> listArticleByCategory(Category category) {
        Query query = getCurrentSession().createQuery("select ac.article from ArticleCategory ac where ac.category.id=:categoryId");
        query.setLong("categoryId", category.getId());
        List<Article> articleList = (List<Article>) query.list();
        return articleList;
    }

    @Override
    public List<Question> listQuestionByCategory(Category category) {
        Query query = getCurrentSession().createQuery("select qc.question from QuestionCategory qc where qc.category.id=:categoryId");
        query.setLong("categoryId", category.getId());
        List<Question> questionList = (List<Question>) query.list();
        return questionList;
    }
}
