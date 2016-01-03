package org.ligson.coderstar2.system.domains;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;

/**
 * Created by ligson on 2015/7/16.
 */
@Entity
@Table(name = "sys_tag")
public class SysTag {
    private long id;
    private String name;
    private String createDate = DateUtils.format();
    private long creatorId;
    private long questionNum = 0;
    private long articleNum = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "create_date", nullable = false)
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Column(name = "creator_id", nullable = false)
    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    @Column(name = "question_num", nullable = false)
    public long getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(long questionNum) {
        this.questionNum = questionNum;
    }

    @Column(name = "article_num", nullable = false)
    public long getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(long articleNum) {
        this.articleNum = articleNum;
    }
}
