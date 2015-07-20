package org.ligson.coderstar2.pay.traderecord.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.pay.domains.TradeRecord;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface TradeRecordDao extends BaseDao<TradeRecord> {
    public List<TradeRecord> findAllByUser(User user, int offset, int max, String sort, String order);
}
