package org.ligson.coderstar2.article.article.dao.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.ligson.coderstar2.article.article.dao.ArticleDao;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ruby on 2015/7/16.
 */
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao {
    private static Logger logger = Logger.getLogger(ArticleDaoImpl.class);

    @Override
    public List<List> countByArticleGroupByUser(String preWeek) {
        List<List> lists = null;
        if (preWeek == null) {
            Query query = getCurrentSession().createQuery("select a.creatorId,count(a.id) from Article a group by a.creatorId order by count(a.id) desc ");
            lists = query.list();
        } else {
            Query query = getCurrentSession().createQuery("select a.creatorId,count(a.id) from Article a where a.createDate>:preWeek group by a.creatorId order by count(a.id) desc ");
            query.setString("preWeek", preWeek);
            lists = query.list();
        }
        if (CollectionUtils.isNotEmpty(lists)) {
            boolean isList = lists.get(0) instanceof List;
            if(isList){
                for (List tmp : lists) {
                    Long creatorId = (Long) tmp.get(0);
                    Query query = getCurrentSession().createQuery("from User where id=:id");
                    query.setLong("id", creatorId);
                    List list = query.list();
                    tmp.set(0, list.get(0));
                }
            }else{

                for (Object object: lists) {
                    Object[] Obj = (Object[]) object;

                    Long creatorId = (Long) Obj[0];
                    Query query = getCurrentSession().createQuery("from User where id=:id");
                    query.setLong("id", creatorId);
                    List list = query.list();
                    Obj[0]=list.get(0);
                }
            }
        }
        return lists;
    }

    @Override
    public List<Article> findAllByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset) {
        String hql = "";
        if (categoryId >= 0 && tagId >= 0) {
            hql = "select DISTINCT(a) from ArticleCategory ac,ArticleTag at,Article a where a.id=at.articleId and at.articleId=ac.articleId and at.tagId=" + tagId + " and a.state=0 and ac.categoryId=" + categoryId + " order by a." + order + " desc";
        } else if (categoryId < 0 && tagId >= 0) {
            hql = "select DISTINCT(a) from ArticleTag at,Article a where a.id=at.articleId and a.state=0 and at.tagId=" + tagId + " order by a." + order + " desc";
        } else if (categoryId >= 0 && tagId < 0) {
            hql = "select DISTINCT(a)  from ArticleCategory ac,Article a where a.id=ac.articleId and  a.state=0 and ac.categoryId=" + categoryId + " order by a." + order + " desc";
        } else {
            //categoryId<0&&tagId<0
            hql = " from Article where state=0 order by " + order + " desc";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        List<Article> articles = (List<Article>) query.list();


        return articles;
    }

    @Override
    public int countByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset) {
        String hql = "";
        if (categoryId >= 0 && tagId >= 0) {
            hql = "select DISTINCT(a) from ArticleCategory ac,ArticleTag at,Article a where a.id=ac.articleId and  at.articleId=ac.articleId and at.tagId=" + tagId + " and ac.categoryId=" + categoryId + " order by a." + order + " desc";
        } else if (categoryId < 0 && tagId >= 0) {
            hql = "select DISTINCT(a) from ArticleTag at,Article a where a.id=at.articleId and at.tagId=" + tagId + " order by a." + order + " desc";
        } else if (categoryId >= 0 && tagId < 0) {
            hql = " from ArticleCategory ac,Article a where a.id=ac.articleId and  ac.categoryId=" + categoryId + " order by a." + order + " desc";
        } else {
            //categoryId<0&&tagId<0
            hql = " from Article order by " + order + " desc";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<Article> articles = (List<Article>) query.list();
        return articles.size();
    }

    @Override
    public List<Article> findAllByStateOrderBy(int statePublish, String sort, String order, int offset, int max) {
        Query query = getCurrentSession().createQuery("from Article a where a.state=:state order by " + sort + " " + order);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        query.setInteger("state", statePublish);
        List<Article> articles = (List<Article>) query.list();
        return articles;
    }

    @Override
    public List<Article> findAllByStateAndCategoryOrderBy(int statePublish, Category category, String sort, String order, int offset, int max) {
        Query query = getCurrentSession().createQuery("select distinct(ac.article) from ArticleCategory ac where ac.article.state=:state and ac.category.id=:categoryId order by ac.article." + sort + " " + order);
        query.setInteger("state", statePublish);
        query.setLong("categoryId", category.getId());
        query.setFirstResult(offset);
        query.setMaxResults(max);
        List<Article> articles = (List<Article>) query.list();
        return articles;
    }

    @Override
    public List<Article> findAllArticleByCreatorAndState(User user, int statePublish, String sort, String order, int offset, int max) {
        Query query = null;
        if (statePublish >= 0) {
            query = getCurrentSession().createQuery("from Article a where a.creatorId=:userId and a.state=:state order by a." + sort + " " + order);
            query.setLong("userId", user.getId());
            query.setInteger("state", statePublish);
        } else {
            query = getCurrentSession().createQuery("from Article a where a.creatorId=:userId  order by a." + sort + " " + order);
            query.setLong("userId", user.getId());
        }

        query.setFirstResult(offset);
        query.setMaxResults(max);
        List<Article> articles = (List<Article>) query.list();
        return articles;
    }

    @Override
    public int countByCreatorAndState(User user, int statePublish) {
        Query query = null;
        if (statePublish >= 0) {
            query = getCurrentSession().createQuery("select count(*) from Article a where a.creatorId=:userId and a.state=:state");
            query.setLong("userId", user.getId());
            query.setInteger("state", statePublish);
        } else {
            query = getCurrentSession().createQuery("select count(*) from Article a where a.creatorId=:userId");
            query.setLong("userId", user.getId());
        }
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }

    @Override
    public List<Article> findAllAttentionArticle(User user, int offset, int max) {
        Query query = getCurrentSession().createQuery("select aa.article from AttentionArticle aa where aa.user.id=:userId order by aa.createDate desc");
        query.setLong("userId", user.getId());
        query.setFirstResult(offset);
        query.setMaxResults(max);
        List<Article> articles = (List<Article>) query.list();
        return articles;
    }

    @Override
    public Map<String, Object> searhArticle(String title, long tagId, long categoryId, int max, int offset, String sort, String orderr) {
        Query query = null;
        Query query2 = null;
        if (tagId >= 0 && categoryId >= 0) {
            query = getCurrentSession().createQuery("select a from Article a,ArticleTag at,ArticleCategory ac where a.id=at.article.id and a.id=ac.article.id and a.title like :title and a.state=0 order by a." + sort + " " + orderr);
            query2 = getCurrentSession().createQuery("select count(a) from Article a,ArticleTag at,ArticleCategory ac where a.id=at.article.id and a.id=ac.article.id and a.title like :title and a.state=0  order by a." + sort + " " + orderr);
        } else if (tagId < 0 && categoryId >= 0) {
            query = getCurrentSession().createQuery("select a from Article a,ArticleCategory ac where a.id=ac.article.id  and a.title like :title and a.state=0  order by a." + sort + " " + orderr);
            query2 = getCurrentSession().createQuery("select count(a) from Article a,ArticleCategory ac where a.id=ac.article.id  and a.title like :title and a.state=0  order by a." + sort + " " + orderr);
        } else {
            query = getCurrentSession().createQuery("select a from Article a,ArticleTag at where a.id=at.article.id  and a.title like :title and a.state=0  order by a." + sort + " " + orderr);
            query2 = getCurrentSession().createQuery("select count(a) from Article a,ArticleTag at where a.id=at.article.id  and a.title like :title and a.state=0  order by a." + sort + " " + orderr);
        }
        query.setString("title", "%" + title + "%");
        query2.setString("title", "%" + title + "%");
        query.setFirstResult((int) offset);
        query.setMaxResults((int) max);
        List<Article> articleList = query.list();
        Map<String, Object> result = new HashMap<>();
        result.put("articleList", articleList);
        Long count = (Long) query2.uniqueResult();
        result.put("total", count.intValue());
        return result;
    }

    @Override
    public void execuRemoveSql(long articleId) throws Exception {
        //通过sql方式进行删除对应数据，涉及标签、类别
        getCurrentSession().createSQLQuery(findRemoveSql(articleId, "article_category")).executeUpdate();
        getCurrentSession().createSQLQuery(findRemoveSql(articleId, "article_tag")).executeUpdate();
        getCurrentSession().createSQLQuery("DELETE FROM article WHERE id=" + articleId).executeUpdate();
    }

    @Override
    public int countByCreatorAndStateAndTitleLike(User user, int statePublish, String title) {
        Query query = null;
        if (statePublish >= 0) {
            String hql = "select count(*) from Article a where a.creatorId=:userId and a.state=:state ";
            if (StringUtils.isNotBlank(title)) {
                hql += " and a.title like :title";
            }
            query = getCurrentSession().createQuery(hql);
            query.setLong("userId", user.getId());
            query.setInteger("state", statePublish);
            if (StringUtils.isNotBlank(title)) {
                query.setString("title", "%" + title + "%");
            }
        } else {
            String hql = "select count(*) from Article a where a.creatorId=:userId ";
            if (StringUtils.isNotBlank(title)) {
                hql += " and a.title like :title";
            }
            query = getCurrentSession().createQuery(hql);
            query.setLong("userId", user.getId());
            if (StringUtils.isNotBlank(title)) {
                query.setString("title", "%" + title + "%");
            }
        }
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }

    @Override
    public List<Article> findAllArticleByUserAndTitleLikeOrder(User user, String title, int offset, int max, String sort, String order) {
        String hql = "select new Article(title,id,createDate,state,replyNum,viewNum,attentionNum,poster,recommendNum) from Article a where a.creatorId=:userId ";
        if (StringUtils.isNotBlank(title)) {
            hql += " and a.title like :title";
        }
        hql += " order by a." + sort + " " + order;
        Query query = getCurrentSession().createQuery(hql);
        query.setLong("userId", user.getId());
        if (StringUtils.isNotBlank(title)) {
            query.setString("title", "%" + title + "%");
        }
        query.setFirstResult(offset);
        query.setMaxResults(max);
        List<Article> articles = (List<Article>) query.list();
        return articles;
    }

    @Override
    public int countByCategoryAndState(Category category, int statePublish) {
        Query query = getCurrentSession().createQuery("select distinct(ac.article) from ArticleCategory ac where ac.category.id=:cId and ac.article.state=:aState");
        query.setLong("cId", category.getId());
        query.setInteger("aState", statePublish);
        return query.list().size();
    }


    /**
     * 组装需要删除的语句
     *
     * @param articleId
     * @param tableName
     * @return
     */
    private String findRemoveSql(long articleId, String tableName) {
        return "DELETE FROM " + tableName + " WHERE article_id=" + articleId + ";";
    }

    @Override
    public List<Article> list(int offset, int max) {
        //String title, long id, String createDate, int state, long replyNum, long viewNum, long attentionNum, String poster, int recommendNum
        Query query = getCurrentSession().createQuery("select new Article(title,id,createDate,state,replyNum,viewNum,attentionNum,poster,recommendNum) from Article");
        query.setFirstResult(offset);
        query.setMaxResults(max);
        return query.list();
    }
}
