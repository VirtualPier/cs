package org.ligson.coderstar2.user.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.user.dao.UserDao;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public List<User> hotAuthors(int max) {
        Query query = getCurrentSession().createQuery("select a.creator from Article a group by a.creator order by count(a.id) desc");
        query.setFirstResult(0);
        query.setMaxResults(max);
        List<User> userList = (List<User>) query.list();
        return userList;
    }

    @Override
    public List<User> hotReplyers(int max) {
        Query query = getCurrentSession().createQuery("select a.creator from Question a where a.rightAsk is not null group by a.creator order by count(a.id) desc");
        query.setFirstResult(0);
        query.setMaxResults(max);
        List<User> userList = (List<User>) query.list();
        return userList;
    }
}
