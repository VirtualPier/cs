package org.ligson.coderstar2.question.rate.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.question.domains.Rate;
import org.ligson.coderstar2.question.rate.dao.RateDao;
import org.ligson.coderstar2.system.domains.Category;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class RateDaoImpl extends BaseDaoImpl<Rate> implements RateDao {
    @Override
    public List<Rate> findAllByAsk(Ask ask) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Rate r where r.ask.id=:aid");
        query.setLong("aid", ask.getId());
        List<Rate> categories = (List<Rate>)query.list();
        return categories;
    }
}
