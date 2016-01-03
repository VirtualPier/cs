package org.ligson.coderstar2.pay.domains;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 * 冻结资金
 */
@Entity
@Table(name = "blocked_fund")
public class BlockedFund {
    private long id;
    private long userId;
    private String createDate = DateUtils.format();
    private double money = 0;
    private int state = 1;
    //1问题,2文章,3提现
    private int objType = 1;
    private long objId = -1;
    private String comments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    @Column(name = "t_state", nullable = false)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Column(name = "obj_type", nullable = false)
    public int getObjType() {
        return objType;
    }

    public void setObjType(int objType) {
        this.objType = objType;
    }

    @Column(name = "obj_id", nullable = false)
    public long getObjId() {
        return objId;
    }

    public void setObjId(long objId) {
        this.objId = objId;
    }

    @Column(name = "comments", nullable = true)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public static final Map<Integer, String> stateCnName = new HashMap<>();
    public static final int STATE_LOCK = 1;
    public static final int STATE_UNLOCK = 2;

    static {
        stateCnName.put(STATE_LOCK, "冻结");
        stateCnName.put(STATE_UNLOCK, "解冻");
    }
}
