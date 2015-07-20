package org.ligson.coderstar2.pay.withdraw.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.pay.domains.Withdraw;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface WithdrawDao extends BaseDao<Withdraw> {
    public List<Withdraw> findAllByUser(User user, int offset, int max, String sort, String order);

    public Withdraw findAllByUserAndState(User user1, int state);
}
