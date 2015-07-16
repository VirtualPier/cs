package org.ligson.coderstar2.pay.domains;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ligson on 2015/7/16.
 * 充值订单
 */
@Entity
@Table(name = "pay_order")
public class PayOrder {
    private long id;
    private String guid = UUID.randomUUID().toString();
    private double money = 0.0;
    private String createDate = DateUtils.format();
    private User user;
    private String comments;
    private int state;
    private int type;
    private String outOrder;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "guid", nullable = false, unique = true)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Column(name = "money", nullable = false)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Column(name = "create_date", nullable = false)
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Column(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "comments", nullable = false)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "order_state", nullable = false)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Column(name = "order_type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Column(name = "out_order", nullable = true)
    public String getOutOrder() {
        return outOrder;
    }

    public void setOutOrder(String outOrder) {
        this.outOrder = outOrder;
    }

    public static final Map<Integer, String> stateCnName = new HashMap<>();
    public static final Map<Integer, String> typeCnName = new HashMap<>();
    static final int STATE_PAYING = 1;
    static final int STATE_SUCEESS = 2;
    static final int STATE_FAIL = 3;
    static final int TYPE_ALIPAY = 1;
    static final int TYPE_WEIXIN = 2;

    static {
        stateCnName.put(STATE_PAYING, "正在充值");
        stateCnName.put(STATE_SUCEESS, "充值成功");
        stateCnName.put(STATE_FAIL, "充值失败");

        typeCnName.put(TYPE_ALIPAY, "支付宝");
        typeCnName.put(TYPE_WEIXIN, "微信");

    }

}
