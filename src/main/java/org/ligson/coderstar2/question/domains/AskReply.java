package org.ligson.coderstar2.question.domains;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 回复的回复
 * Created by ligson on 2015/7/16.
 */
@Entity
@Table(name = "ask_reply")
public class AskReply implements Serializable {
    private long id;
    private long askId;
    private long userId;
    private long atUserId;
    private String createDate = DateUtils.format();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "ask_id", nullable = false)
    public long getAskId() {
        return askId;
    }

    public void setAskId(long askId) {
        this.askId = askId;
    }

    @Column(nullable = false, name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "at_user_id", nullable = false)
    public long getAtUserId() {
        return atUserId;
    }

    public void setAtUserId(long atUserId) {
        this.atUserId = atUserId;
    }

    @Column(name = "create_date", nullable = false)
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
