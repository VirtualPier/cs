package org.ligson.coderstar2.pay.domains;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;

/**
 * Created by ligson on 2015/7/16.
 * 提现
 */
@Entity
@Table(name = "withdraw")
public class Withdraw {
    private long id;
    private User user;
    private String createDate = DateUtils.format();
    private double money = 0.0;
    private int state;
    private String comments;
    private String payAccount;
    //审批日期
    private String allowDate;
    //实际给用户的金钱
    private double trueMoney = 0.0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "create_date", nullable = false)
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Column(name = "money", nullable = false)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Column(name = "w_state", nullable = false)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Column(name = "comments", nullable = true)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "pay_account", nullable = true)
    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    @Column(name = "allow_date", nullable = false)
    public String getAllowDate() {
        return allowDate;
    }

    public void setAllowDate(String allowDate) {
        this.allowDate = allowDate;
    }

    @Column(name = "true_money", nullable = true)
    public double getTrueMoney() {
        return trueMoney;
    }

    public void setTrueMoney(double trueMoney) {
        this.trueMoney = trueMoney;
    }
}
