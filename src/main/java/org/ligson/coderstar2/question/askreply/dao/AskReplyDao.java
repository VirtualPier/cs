package org.ligson.coderstar2.question.askreply.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.question.domains.AskReply;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface AskReplyDao extends BaseDao<AskReply> {
    public List<AskReply> findAllByAsk(Ask ask);
}
