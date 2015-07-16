package org.ligson.coderstar2.question.domains;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.util.Date;

/**
 * 回复的回复
 * Created by ligson on 2015/7/16.
 */
@Entity
@Table(name = "ask_reply")
public class AskReply {
    private long id;
    private Ask ask;
    private User user;
    private User atUser;
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
    public Ask getAsk() {
        return ask;
    }

    public void setAsk(Ask ask) {
        this.ask = ask;
    }

    @Column(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "at_user_id", nullable = true)
    public User getAtUser() {
        return atUser;
    }

    public void setAtUser(User atUser) {
        this.atUser = atUser;
    }

    @Column(name = "create_date", nullable = false)
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
