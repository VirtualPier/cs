package org.ligson.coderstar2.user.dao.impl;

import org.hibernate.Query;
import org.ligson.coderstar2.base.dao.impl.BaseDaoImpl;
import org.ligson.coderstar2.user.dao.UserDao;
import org.ligson.coderstar2.user.domains.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public List<User> hotAuthors(int max) {
        Query query = getCurrentSession().createQuery("select a.creatorId from Article a group by a.creatorId order by count(a.id) desc");
        query.setFirstResult(0);
        query.setMaxResults(max);

        List<Long> userIdList = query.list();
        List<User> userList = new ArrayList<>();
        Query query1 = getCurrentSession().createQuery("from User u where u.id in (:idList)");
        query1.setParameterList("idList", userIdList);
        userList = query1.list();
        return userList;
    }

    @Override
    public List<User> hotReplyers(int max) {
        Query query = getCurrentSession().createQuery("SELECT a.userId FROM Question q,Ask a where q.rightAskId IS NOT NULL AND q.rightAskId=a.id GROUP BY a.userId ORDER BY COUNT(q.id) DESC");
        query.setFirstResult(0);
        query.setMaxResults(max);
        List<Long> userIdList = query.list();
        Query query1 = getCurrentSession().createQuery("from User where id in (:idList)");
        query1.setParameterList("idList", userIdList);
        List<User> userList = query1.list();
        return userList;
    }
}
