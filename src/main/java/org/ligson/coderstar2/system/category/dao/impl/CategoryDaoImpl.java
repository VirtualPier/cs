package org.ligson.coderstar2.system.category.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionCategory;
import org.ligson.coderstar2.question.domains.QuestionTag;
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
        String hql = "select qc.category from QuestionCategory qc where qc.question.id=:qId";
        Query query = session.createQuery(hql);
        query.setLong("qId", question.getId());
        List<Category> categories = (List<Category>) query.list();
        return categories;
    }

    @Override
    public List<Category> findAllByArticle(Article article) {
        Session session = getCurrentSession();
        String hql = "select ac.category from ArticleCategory ac where ac.article.id=:aId";
        Query query = session.createQuery(hql);
        query.setLong("aId", article.getId());
        List<Category> categories = (List<Category>) query.list();
        return categories;
    }
}
