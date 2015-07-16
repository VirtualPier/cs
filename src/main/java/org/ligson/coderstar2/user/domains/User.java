package org.ligson.coderstar2.user.domains;

import com.boful.common.date.utils.DateUtils;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户表
 * Created by ligson on 2015/7/16.
 */
@Entity
@Table(name = "cs_user")
public class User {
    private long id;
    private String nickName;
    private String password;
    private String cellphone;
    private String email;
    private int sex = SEX_MALE;
    private String photo;
    private String birth = DateUtils.format();
    private String registerDate = DateUtils.format();
    private String createName;
    private String lastLoginDate = DateUtils.format();
    private int state = STATE_NORMAL;
    private int role = ROLE_USER;
    private String introduce;
    private int questionNum = 0;
    private String qq;
    private String web;
    //可用余额
    private double balance = 0;
    //冻结资金
    private double blockedFund = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, name = "nick_name")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(nullable = false, name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false, name = "cellphone", length = 32, unique = true)
    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Column(nullable = false, name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false, name = "sex")
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Column(nullable = true, name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(nullable = true, name = "birth")
    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Column(nullable = false, name = "register_date")
    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    @Column(nullable = true, name = "create_name")
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Column(nullable = false, name = "last_login_date")
    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Column(nullable = false, name = "user_state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Column(nullable = false, name = "user_role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Column(nullable = true, name = "introduce")
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Column(nullable = false, name = "question_num")
    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    @Column(nullable = true, name = "qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Column(nullable = true, name = "web")
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Column(nullable = false, name = "balance")
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Column(nullable = false, name = "blocked_fund")
    public double getBlockedFund() {
        return blockedFund;
    }

    public void setBlockedFund(double blockedFund) {
        this.blockedFund = blockedFund;
    }


    public static final Map<Integer, String> stateCnName = new HashMap<>();
    public static final Map<Integer, String> roleCnName = new HashMap<>();
    public static final Map<Integer, String> sexCnName = new HashMap<>();

    public static final int STATE_DISABLED = 0;
    public static final int STATE_NORMAL = 1;
    public static final int STATE_APPLY = 2;

    public static final int ROLE_SUPER = 0;
    public static final int ROLE_MANAGER = 1;
    public static final int ROLE_USER = 2;

    public static final int SEX_FEMALE = 0;
    public static final int SEX_MALE = 1;

    static {
        stateCnName.put(0, "禁用");
        stateCnName.put(1, "正常");
        stateCnName.put(2, "待审核");

        roleCnName.put(0, "超级管理员");
        roleCnName.put(1, "管理员");
        roleCnName.put(2, "普通用户");

        sexCnName.put(0, "女");
        sexCnName.put(1, "男");

    }
}
