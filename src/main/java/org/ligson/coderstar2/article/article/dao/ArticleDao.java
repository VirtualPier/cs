package org.ligson.coderstar2.article.article.dao;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.base.dao.BaseDao;

import java.util.List;

/**
 * Created by Ruby on 2015/7/16.
 */
public interface ArticleDao extends BaseDao<Article> {
    public List<List> countByArticleGroupByUser(String preWeek);

    List<Article> findAllByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset);

    int countByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset);
}
