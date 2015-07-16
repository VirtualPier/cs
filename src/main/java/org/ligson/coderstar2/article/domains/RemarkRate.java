package org.ligson.coderstar2.article.domains;

import com.boful.common.date.utils.DateUtils;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.ligson.coderstar2.user.domains.User;
import java.util.Date;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name="remark_rate")
public class RemarkRate {
    private long id;
    //评价问题
    private Remark remark;
    //评价用户
    private User user;
    //赞一个
    private boolean isSupport = false;
    private String createDate = DateUtils.format();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "remark_id", referencedColumnName = "id")
    public Remark getRemark() {
        return remark;
    }

    public void setRemark(Remark remark) {
        this.remark = remark;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name="is_support",nullable = false)
    @Type(type = "byte")
    public boolean isSupport() {
        return isSupport;
    }

    public void setSupport(boolean isSupport) {
        this.isSupport = isSupport;
    }

    @Column(nullable = false, name = "create_date")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
