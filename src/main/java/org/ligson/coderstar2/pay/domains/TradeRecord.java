package org.ligson.coderstar2.pay.domains;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 * 交易记录
 */
@Entity
@Table(name = "trade_record")
public class TradeRecord {
    private long id;
    private int type;
    private long objId;
    //1问题,2文章,3提现
    private int objType;
    private String createDate = DateUtils.format();
    private double money = 0.0;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "record_type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Column(name = "obj_id", nullable = false)
    public long getObjId() {
        return objId;
    }

    public void setObjId(long objId) {
        this.objId = objId;
    }

    @Column(name = "obj_type", nullable = false)
    public int getObjType() {
        return objType;
    }

    public void setObjType(int objType) {
        this.objType = objType;
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

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static final int Type_PAY = 1;
    public static final int Type_INCOME = 2;
    public static final Map<Integer, String> typeCnName = new HashMap();

    static {
        typeCnName.put(Type_PAY, "支出");
        typeCnName.put(Type_INCOME, "收入");
    }

}
