package org.ligson.coderstar2.user.service;

import org.ligson.coderstar2.pay.domains.TradeRecord;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.user.domains.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
    public Map register(String email, String nickName, String cellphone, String password);

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
    public Map addUser(String nickName, String cellphone, String createName, int state, int role, String birth, String email, int sex, String qq, String web, String introduce);

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
    public Map updateUser(String nickName, String cellphone, String createName, int state, int role, String birth, String email, int sex, String qq, String web, String introduce);

    /***
     * 删除用户
     *
     * @param id
     * @return
     */
    public Map removeUser(long id);

    /***
     * 退出
     *
     * @param params
     * @return
     */
    public Map logout(Map params);

    /***
     * 修改头像
     *
     * @param photo
     * @return
     */
    public Map modifyPhoto(CommonsMultipartFile photo);

    /***
     * 重置密码
     *
     * @param userId
     * @param old_password
     * @param new_password
     * @return
     */
    public Map resetPassword(long userId, String old_password, String new_password);

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

}
