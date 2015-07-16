package org.ligson.coderstar2.question.domains;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 关注问题
 * Created by ligson on 2015/7/16.
 */
public class AttentionQuestion {
    private long id;
    private User user;
    private Question question;
    private String createDate = DateUtils.format();

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

    @Column(name = "question_id", nullable = false)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Column(name = "create_date", nullable = false)
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
