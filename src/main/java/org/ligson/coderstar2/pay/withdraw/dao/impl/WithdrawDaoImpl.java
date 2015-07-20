package org.ligson.coderstar2.pay.withdraw.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.pay.domains.Withdraw;
import org.ligson.coderstar2.pay.withdraw.dao.WithdrawDao;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class WithdrawDaoImpl extends BaseDaoImpl<Withdraw> implements WithdrawDao {
    @Override
    public List<Withdraw> findAllByUser(User user, int offset, int max, String sort, String order) {
        Query query = getCurrentSession().createQuery("from Withdraw w where w.user.id=:userId order by " + sort + " " + order);
        query.setLong("userId", user.getId());
        query.setFirstResult(offset);
        query.setMaxResults(max);
        List<Withdraw> withdrawList = (List<Withdraw>) query.list();
        return withdrawList;
    }

    @Override
    public Withdraw findAllByUserAndState(User user1, int state) {
        Query query = getCurrentSession().createQuery(" from Withdraw w where w.user.id=:userId and w.state=:state ");
        query.setLong("userId", user1.getId());
        query.setLong("state", state);
        List<Withdraw> withdraws = (List<Withdraw>) query.list();
        if (withdraws.size() > 0) {
            return withdraws.get(0);
        } else {
            return null;
        }
    }
}
