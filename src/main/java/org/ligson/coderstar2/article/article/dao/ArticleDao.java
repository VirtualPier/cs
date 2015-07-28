package org.ligson.coderstar2.article.article.dao;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Ruby on 2015/7/16.
 */
public interface ArticleDao extends BaseDao<Article> {
    public List<List> countByArticleGroupByUser(String preWeek);

    List<Article> findAllByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset);

    public int countByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset);

    public List<Article> findAllByStateOrderBy(int statePublish, String sort, String order, int offset, int max);

    public List<Article> findAllByStateAndCategoryOrderBy(int statePublish, Category category, String sort, String order, int offset, int max);

    public List<Article> findAllArticleByCreatorAndState(User user, int statePublish, String sort, String order, int offset, int max);

    public int countByCreatorAndState(User user, int statePublish);

    public List<Article> findAllAttentionArticle(User user, int offset, int max);

    public Map<String, Object> searhArticle(String title, long tagId, long categoryId, int max, int offset, String sort, String orderr);

    void execuRemoveSql(long articleId) throws Exception;

    public int countByCreatorAndStateAndTitleLike(User user, int state, String title);

    public List<Article> findAllArticleByUserAndTitleLikeOrder(User user, String title, int offset, int max, String sort, String order);
}
