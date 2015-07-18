package org.ligson.coderstar2.system.systag.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.system.systag.dao.SysTagDao;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class SysTagDaoImpl extends BaseDaoImpl<SysTag> implements SysTagDao {
    @Override
    public List<SysTag> findAllByQuestion(Question question) {
        Query query = getCurrentSession().createQuery("select qt.tag from QuestionTag qt where qt.question.id=:qId");
        query.setLong("qId", question.getId());
        List<SysTag> tags = (List<SysTag>) query.list();
        return tags;
    }
}
