package org.ligson.coderstar2.question.askreply.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.askreply.dao.AskReplyDao;
import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.question.domains.AskReply;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class AskReplyDaoImpl extends BaseDaoImpl<AskReply> implements AskReplyDao {
    @Override
    public List<AskReply> findAllByAsk(Ask ask) {
        Query query = getCurrentSession().createQuery("from AskReply ar where ar.ask.id=:askId");
        query.setLong("askId", ask.getId());
        List<AskReply> askReplyList = (List<AskReply>) query.list();

        return askReplyList;
    }
}
