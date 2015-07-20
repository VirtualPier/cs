package org.ligson.coderstar2.pay.traderecord.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.pay.domains.TradeRecord;
import org.ligson.coderstar2.pay.traderecord.dao.TradeRecordDao;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class TradeRecordDaoImpl extends BaseDaoImpl<TradeRecord> implements TradeRecordDao {
    @Override
    public List<TradeRecord> findAllByUser(User user, int offset, int max, String sort, String order) {
        Query query = getCurrentSession().createQuery("from TradeRecord tr where tr.user.id=:userId order by " + sort + " " + order);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        query.setLong("userId", user.getId());
        List<TradeRecord> tradeRecords = (List<TradeRecord>) query.list();
        return tradeRecords;
    }
}
