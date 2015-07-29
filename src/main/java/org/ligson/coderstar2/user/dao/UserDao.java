package org.ligson.coderstar2.user.dao;

import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface UserDao extends BaseDao<User> {
    public List<User> hotAuthors(int max);

    public List<User> hotReplyers(int max);
}
