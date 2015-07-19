package org.ligson.coderstar2.article.remark.dao.impl;

import org.hibernate.Query;
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
        List<RemarkRate> remarkDaos = query.list();
        if (remarkDaos.size() > 0) {
            return remarkDaos.get(0);
        } else {
            return null;
        }
    }
}
