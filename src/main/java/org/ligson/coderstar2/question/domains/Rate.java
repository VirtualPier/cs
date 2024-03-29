package org.ligson.coderstar2.question.domains;

import com.boful.common.date.utils.DateUtils;
import org.hibernate.annotations.Type;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;

/**
 * 评价表
 * Created by ligson on 2015/7/16.
 */
@Entity
@Table(name = "rate")
public class Rate {
    private long id;
    //评价问题
    private Ask ask;
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
    @JoinColumn(name = "ask_id", referencedColumnName = "id", nullable = false)
    public Ask getAsk() {
        return ask;
    }

    public void setAsk(Ask ask) {
        this.ask = ask;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "is_support", nullable = false)
    public boolean isSupport() {
        return isSupport;
    }

    public void setIsSupport(boolean isSupport) {
        this.isSupport = isSupport;
    }

    public void setSupport(boolean isSupport) {
        this.isSupport = isSupport;
    }

    @Column(name = "create_date", nullable = false)
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
