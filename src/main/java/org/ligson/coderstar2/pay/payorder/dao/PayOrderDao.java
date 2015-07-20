package org.ligson.coderstar2.pay.payorder.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.pay.domains.PayOrder;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface PayOrderDao extends BaseDao<PayOrder> {
    public List<PayOrder> findAllByUser(User user, int offset, int max);

    public int countByUser(User user);
}
