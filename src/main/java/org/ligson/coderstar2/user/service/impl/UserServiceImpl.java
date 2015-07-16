package org.ligson.coderstar2.user.service.impl;

import org.ligson.coderstar2.user.dao.UserDao;
import org.ligson.coderstar2.user.service.UserService;

/**
 * Created by ligson on 2015/7/16.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
