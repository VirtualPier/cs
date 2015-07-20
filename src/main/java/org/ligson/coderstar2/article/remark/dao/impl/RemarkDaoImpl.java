package org.ligson.coderstar2.article.remark.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.Remark;
import org.ligson.coderstar2.article.domains.RemarkRate;
import org.ligson.coderstar2.article.remark.dao.RemarkDao;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by Ruby on 2015/7/16.
 */
public class RemarkDaoImpl extends BaseDaoImpl<Remark> implements RemarkDao {
    @Override
    public RemarkRate findByUserAndRemark(User currentUser, Remark remark) {
        Query query = getCurrentSession().createQuery("from RemarkRate rd where rd.user.id=:userId and rd.remark.id=:rId ");
        query.setLong("userId", currentUser.getId());
        query.setLong("rId", remark.getId());
        List<RemarkRate> remarkDaos = query.list();
        if (remarkDaos.size() > 0) {
            return remarkDaos.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Remark> findAllByArticleOrderBy(Article article, String remarkSort, String desc) {
        Query query = getCurrentSession().createQuery("from Remark r where r.article.id=:aId order by " + remarkSort + " " + desc);
        query.setLong("aId", article.getId());
        List<Remark> remarks = (List<Remark>) query.list();
        return remarks;
    }
}
