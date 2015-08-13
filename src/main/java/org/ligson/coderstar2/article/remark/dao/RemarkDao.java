package org.ligson.coderstar2.article.remark.dao;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.Remark;
import org.ligson.coderstar2.article.domains.RemarkRate;
import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by Ruby on 2015/7/16.
 */
public interface RemarkDao extends BaseDao<Remark> {
    public RemarkRate findByUserAndRemark(User currentUser, Remark remark);

    public List<Remark> findAllByArticleOrderBy(Article article, String remarkSort, String desc);

    public List<Remark> findAllByArticleOrderBy(Article article, int offset, int max, String sort, String order);
}
