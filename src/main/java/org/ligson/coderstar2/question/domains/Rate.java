package org.ligson.coderstar2.question.domains;

import com.boful.common.date.utils.DateUtils;
import org.hibernate.annotations.Type;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 评价表
 * Created by ligson on 2015/7/16.
 */
@Entity
@Table(name = "rate")
public class Rate implements Serializable {
    private long id;
    //评价问题
    private long askId;
    //评价用户
    private long userId;
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

    @Column(nullable = false, name = "question_id")
    public long getAskId() {
        return askId;
    }

    public void setAskId(long askId) {
        this.askId = askId;
    }

    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
