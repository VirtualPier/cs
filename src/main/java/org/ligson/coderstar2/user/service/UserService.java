package org.ligson.coderstar2.user.service;

import org.ligson.coderstar2.pay.domains.TradeRecord;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.user.domains.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
public interface UserService {

    /***
     * 登陆
     *
     * @param name
     * @param password
     * @return
     */
    public Map<String, Object> login(String name, String password);

    /***
     * 注册用户
     *
     * @param email
     * @param nickName
     * @param cellphone
     * @param password
     * @return
     */
    public Map<String, Object> register(String email, String nickName, String cellphone, String password);

    /***
     * 新增用户
     *
     * @param nickName
     * @param cellphone
     * @param createName
     * @param state
     * @param role
     * @param birth
     * @param email
     * @param sex
     * @param qq
     * @param web
     * @param introduce
     * @return
     */
    public Map<String, Object> addUser(String nickName, String cellphone, String createName, int state, int role, String birth, String email, int sex, String qq, String web, String introduce);

    /***
     * 更新用户
     *
     * @param nickName
     * @param cellphone
     * @param createName
     * @param state
     * @param role
     * @param birth
     * @param email
     * @param sex
     * @param qq
     * @param web
     * @param introduce
     * @return
     */
    public Map<String, Object> updateUser(String nickName, String cellphone, String createName, int state, int role, String birth, String email, int sex, String qq, String web, String introduce);

    /***
     * 删除用户
     *
     * @param id
     * @return
     */
    public Map<String, Object> removeUser(long id);

    /***
     * 退出
     *
     * @param
     * @return
     */
    public Map<String, Object> logout(HttpServletRequest request);

    /***
     * 修改头像
     *
     * @param photo
     * @return
     */
    public Map<String, Object> modifyPhoto(CommonsMultipartFile photo, User user);

    /***
     * 重置密码
     *
     * @param userId
     * @param old_password
     * @param new_password
     * @return
     */
    public Map<String, Object> resetPassword(User adminUser, long userId, String old_password, String new_password);

    /***
     * 关于用户更多的问题
     *
     * @param creator
     * @param offset
     * @param max
     * @param actionname
     * @return
     */
    public List<Question> myMoreQuestion(User creator, int offset, int max, String actionname);

    /***
     * @param creator
     * @param offset
     * @param max
     * @param actionname
     * @return
     */
    public List<TradeRecord> myMoreTrade(User creator, int offset, int max, String actionname);

    /***
     * 用户提问的问题
     *
     * @param creator
     * @param max
     * @param offset
     * @return
     */
    public List<Question> myPublish(User creator, int max, int offset);

    /***
     * 用户关注的问题
     *
     * @param creator
     * @param max
     * @param offset
     * @return
     */
    public List<Question> myAttention(User creator, int max, int offset);

    /***
     * 用户回复的问题
     *
     * @param creator
     * @param max
     * @param offset
     * @return
     */
    public List<Question> myReply(User creator, int max, int offset);


    public void initSuper();

    public Map<String, Object> listUser(int offset, int max);

    public Map<String, Object> modifyUserState(long[] ids, int state);

    public User findUserById(long id);

    public Map<String, Object> updateUser(User user, String nickName, int sex, String introduce, String qq, String cellphone, String email, String web);

    public boolean cellphoneIsUnique(String cellphone, User user);

    public boolean emailIsUnique(String email);

    public Map<String, Object> uploadFile(User user, CommonsMultipartFile upload);

    public Map<String, Object> uploadPhoto(User user, CommonsMultipartFile upload);

    public List<User> hotAuthors(int max);

    public List<User> hotReplyers(int max);

    public boolean cellphoneIsUnique(String cellphone);

    public User findUserByEmail(String email);

    public String emailResetPasswordKey(User user);

    public Map<String, Object> resetPwdByIdAndKey(long id, String key, String password);
}
