package org.ligson.coderstar2.user.service.impl;

import org.ligson.coderstar2.pay.domains.TradeRecord;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.user.dao.UserDao;
import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Map;

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

    @Override
    public Map login(String name, String password) {
        return null;
    }

    @Override
    public Map register(String email, String nickName, String cellphone, String password) {
        return null;
    }

    @Override
    public Map addUser(String nickName, String cellphone, String createName, int state, int role, String birth, String email, int sex, String qq, String web, String introduce) {
        return null;
    }

    @Override
    public Map updateUser(String nickName, String cellphone, String createName, int state, int role, String birth, String email, int sex, String qq, String web, String introduce) {
        return null;
    }

    @Override
    public Map removeUser(long id) {
        return null;
    }

    @Override
    public Map logout(Map params) {
        return null;
    }

    @Override
    public Map modifyPhoto(CommonsMultipartFile photo) {
        return null;
    }

    @Override
    public Map resetPassword(long userId, String old_password, String new_password) {
        return null;
    }

    @Override
    public List<Question> myMoreQuestion(User creator, int offset, int max, String actionname) {
        return null;
    }

    @Override
    public List<TradeRecord> myMoreTrade(User creator, int offset, int max, String actionname) {
        return null;
    }

    @Override
    public List<Question> myPublish(User creator, int max, int offset) {
        return null;
    }

    @Override
    public List<Question> myAttention(User creator, int max, int offset) {
        return null;
    }

    @Override
    public List<Question> myReply(User creator, int max, int offset) {
        return null;
    }
}
