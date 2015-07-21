package org.ligson.coderstar2.user.service.impl;

import com.boful.common.codec.utils.PasswordCodec;
import com.boful.common.date.utils.DateUtils;
import com.boful.common.file.utils.FileUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.ligson.coderstar2.pay.domains.TradeRecord;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.user.dao.UserDao;
import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by ligson on 2015/7/16.
 */
public class UserServiceImpl implements UserService {
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    private final String UPLOAD = File.separator+"assets"+File.separator+"images"+File.separator+"upload";

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Map<String, Object> login(String name, String password) {
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
        propValues.add(PasswordCodec.encode(password));
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
    public Map<String, Object> register(String email, String nickName, String cellphone, String password) {
        Map<String, Object> result = new HashMap<>();
        User user = new User();
        user.setCellphone(cellphone);
        //对密码进行加密
        user.setPassword(PasswordCodec.encode(password));
        user.setNickName(nickName);
        user.setEmail(email);
        //验证是否保存成功
        userDao.saveOrUpdate(user);
        result.put("success", true);
        result.put("user", user);
        return result;
    }

    @Override
    public Map<String, Object> addUser(String nickName, String cellphone, String password, int state, int role, String birth, String email, int sex, String qq, String web, String introduce) {
        User user = new User();
        user.setNickName(nickName);
        user.setCellphone(cellphone);
        user.setPassword(PasswordCodec.encode(password));
        user.setEmail(email);
        user.setWeb(web);
        user.setSex(sex);
        user.setQq(qq);
        user.setState(state);
        user.setRole(role);
        user.setBirth(birth);
        user.setIntroduce(introduce);
        userDao.saveOrUpdate(user);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("user", user);
        return result;
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
    public Map<String, Object> logout(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);
        request.getSession().invalidate();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        return result;
    }

    @Override
    public Map<String,Object> modifyPhoto(CommonsMultipartFile photo, User user) {
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = false;
        String url = "";
        String errorMsg = "";
        String contentType = photo.getContentType();
        if(contentType.contains("image")){
            //表明上传文件为img,需要保存到服务器段，数据库关联对应关系
            try {
                InputStream in = photo.getInputStream();
                String webPath = findWebProPath();
                String webUpload = UPLOAD+File.separator+user.getId()+"_"+photo.getOriginalFilename();
                String path = webPath+webUpload;
                File file = new File(path);
                if(!file.exists()){
                    file.getParentFile().mkdirs();
                }
                if(!webPath.isEmpty()){
                    FileUtils.copyFile(in,file);
                    url = ".."+File.separator+".."+webUpload;//文件保存到服务器地址路径
                    flag = true;
                    user.setPhoto(url);
                    userDao.saveOrUpdate(user);
                }else{
                    errorMsg = "获取web项目路径是出错了，请重新尝试";
                }
            } catch (IOException e) {
                logger.error("在用户修改上传图像时出错了："+e.getMessage());
                errorMsg = "保存上传图像时出错了";
            }

        }else{
            errorMsg = "上传的文件不是图形文件，请确认";
        }
        map.put("success",flag);
        map.put("url",url);
        map.put("msg",errorMsg);
        return map;
    }

    /**
     * 获取web项目在服务器所在路径
     * @return
     */
    private String findWebProPath(){
        String classPath = "";
        String path = this.getClass().getResource("/").getPath();
        try {
            classPath = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("类路径为："+path+",转码码为UTF-8时出错");
        }
        if(classPath.isEmpty()){
            return "";
        }
        String webProPath =  new File(classPath).getParentFile().getParent();
        return webProPath;
    }

    @Override
    public Map resetPassword(User user, long userId, String old_password, String new_password) {
        Map<String, Object> result = new HashMap<String, Object>();
        User resetUser = null;
        if (user.getRole() == User.ROLE_SUPER || user.getRole() == User.ROLE_MANAGER) {
            resetUser = userDao.getById(userId);
        } else if (user.getId() == userId) {
            List<String> props = new ArrayList<>();
            List<Object> propValues = new ArrayList<>();
            props.add("id");
            props.add("password");
            propValues.add(user.getId());
            propValues.add(old_password);
            resetUser = userDao.findByAnd(props, propValues);
        }
        if (resetUser != null) {
            resetUser.setPassword(PasswordCodec.encode(new_password));
            userDao.saveOrUpdate(resetUser);
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("isExsit", true);
        }
        return result;
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
            Map<String, Object> result = addUser(nickName, cellphone, password, state, role, null, email, sex, null, null, null);
            boolean success = (boolean) result.get("success");
            if (success) {
                logger.info("超级管理员初始化成功！手机：1383838521，email：admin@admin.com,密码:password");
            } else {
                logger.error("init fail........");
            }
        }
    }

    @Override
    public Map<String, Object> listUser(int offset, int max) {
        List<User> userList = userDao.list(offset, max);
        int total = userDao.countAll();
        Map<String, Object> result = new HashMap<>();
        result.put("rows", userList);
        result.put("total", total);
        return result;
    }

    @Override
    public Map<String, Object> modifyUserState(long[] ids, int state) {
        for (int i = 0; i < ids.length; i++) {
            User user = userDao.getById(ids[i]);
            user.setState(state);
            userDao.saveOrUpdate(user);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @Override
    public User findUserById(long id) {
        return userDao.getById(id);
    }

    @Override
    public Map<String, Object> updateUser(User user, String nickName, int sex, String introduce, String qq, String cellphone, String email, String web) {
        Map<String,Object> map = new HashMap<>();
        try {
            user.setNickName(nickName);
            user.setSex(sex);
            user.setIntroduce(introduce);
            user.setQq(qq);
            user.setCellphone(cellphone);
            user.setEmail(email);
            user.setWeb(web);
            userDao.saveOrUpdate(user);
            map.put("success",true);
        } catch (Exception e) {
            map.put("success",false);
            map.put("msg","跟新用户对象时出错了");
            logger.error("请检查数据结构，更新数据时出错了："+e.getMessage());

        }

        return map;
    }

    @Override
    public boolean cellphoneIsUnique(String cellphone, User user) {
        long count = userDao.countExceptUserBy("cellphone", cellphone,user);
        return count == 0;
    }

    @Override
    public boolean emailIsUnique(String email) {
        long count = userDao.countBy("email", email);
        return count == 0;
    }
}
