package org.ligson.coderstar2.system.systag.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.domains.SysTag;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface SysTagDao extends BaseDao<SysTag> {
    public List<SysTag> findAllByQuestion(Question question);
}
