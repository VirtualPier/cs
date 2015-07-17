package org.ligson.coderstar2.user.service.impl;

import com.boful.common.codec.utils.PasswordCodec;
import com.boful.common.date.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.ligson.coderstar2.pay.domains.TradeRecord;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.user.dao.UserDao;
import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.*;

/**
 * Created by ligson on 2015/7/16.
 */
public class UserServiceImpl implements UserService {
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Map login(String name, String password) {
        //相当于java的map定义
        Map<String, Object> result = new HashMap<>();
        EmailValidator validator = EmailValidator.getInstance();

        User user = null;
        List<String> props = new ArrayList<>();
        List<Object> propValues = new ArrayList<>();
        if (validator.isValid(name)) {
            props.add("email");
        } else {
            props.add("cellphone");
        }
        props.add("password");
        propValues.add(name);
        props.add(PasswordCodec.encode(password));
        user = userDao.findByAnd(props, propValues);
        if (user != null) {
            user.setLastLoginDate(DateUtils.format());
            userDao.saveOrUpdate(user);
            result.put("success", true);
            result.put("user", user);
        } else {
            result.put("success", false);
            result.put("msg", "用户账户不正确或者密码错误!");
        }

        return result;
    }

    @Override
    public Map register(String email, String nickName, String cellphone, String password) {
        return null;
    }

    @Override
    public Map<String, Object> addUser(String nickName, String cellphone, String createName, int state, int role, String birth, String email, int sex, String qq, String web, String introduce) {
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

    @Override
    public void initSuper() {
        long count = userDao.countBy("role", 0);
        if (count == 0) {
            String nickName = "超级管理员";
            String cellphone = "13838385438";
            String email = "admin@admin.com";
            String password = "password";
            int state = User.STATE_NORMAL;
            int role = User.ROLE_SUPER;
            int sex = User.SEX_FEMALE;
            Map<String, Object> result = addUser(nickName, cellphone, null, state, role, null, email, sex, null, null, null);
            boolean success = (boolean) result.get("success");
            if (success) {
                logger.info("超级管理员初始化成功！手机：1383838521，email：admin@admin.com,密码:password");
            } else {
                logger.error("init fail........");
            }
        }
    }
}
