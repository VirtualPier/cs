package org.ligson.coderstar2.pay.payorder.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.pay.domains.PayOrder;
import org.ligson.coderstar2.pay.payorder.dao.PayOrderDao;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class PayOrderDaoImpl extends BaseDaoImpl<PayOrder> implements PayOrderDao {

    @Override
    public List<PayOrder> findAllByUser(User user, int offset, int max) {
        Query query = getCurrentSession().createQuery("from PayOrder po where po.user.id=:userId");
        query.setLong("userId", user.getId());
        query.setMaxResults(max);
        query.setFirstResult(offset);
        List<PayOrder> orderList = query.list();
        return orderList;
    }

    @Override
    public int countByUser(User user) {
        Query query = getCurrentSession().createQuery("select count(*) from PayOrder po where po.user.id=:userId");
        query.setLong("userId", user.getId());
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }
}
