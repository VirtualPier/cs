package org.ligson.coderstar2.question.rate.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.question.domains.Rate;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface RateDao extends BaseDao<Rate> {
    public List<Rate> findAllByAsk(Ask ask);

    public Rate findByAskAndUser(Ask ask, User user);
}
