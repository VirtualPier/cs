package org.ligson.coderstar2.question.domains;

import com.boful.common.date.utils.DateUtils;
import org.hibernate.annotations.Type;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 问题回答表
 * Created by ligson on 2015/7/16.
 */
@Entity
@Table(name = "ask")
public class Ask {
    private long id;
    private String content;
    private long userId;
    private long questionId;
    //支持
    private int supportNum = 0;
    //反对
    private int opposeNum = 0;
    private String createDate = DateUtils.format();
    //private Set<Rate> rates = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "content", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "question_id", nullable = false)
    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @Column(name = "support_num", nullable = false)
    public int getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(int supportNum) {
        this.supportNum = supportNum;
    }

    @Column(name = "oppose_num", nullable = false)
    public int getOpposeNum() {
        return opposeNum;
    }

    public void setOpposeNum(int opposeNum) {
        this.opposeNum = opposeNum;
    }

    @Column(name = "create_date", nullable = false, length = 32)
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}
