package org.ligson.coderstar2.article.article.dao;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.system.domains.Category;

import java.util.List;

/**
 * Created by Ruby on 2015/7/16.
 */
public interface ArticleDao extends BaseDao<Article> {
    public List<List> countByArticleGroupByUser(String preWeek);

    List<Article> findAllByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset);

    public int countByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset);

    public List<Article> findAllByStateOrderBy(int statePublish, String sort, String order, int offset, int max);

    public List<Article> findAllByStateAndCategoryOrderBy(int statePublish, Category category, String sort, String order, int offset, int max);

}
