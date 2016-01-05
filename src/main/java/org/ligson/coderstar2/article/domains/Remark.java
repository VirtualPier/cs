package org.ligson.coderstar2.article.domains;

import com.boful.common.date.utils.DateUtils;
import org.hibernate.annotations.Type;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name = "remark")
public class Remark implements Serializable {
    private long id;
    private String content;
    private long userId;
    private long articleId;
    //支持
    private int supportNum;
    //反对
    private int opposeNum;
    private String createDate = DateUtils.format();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = true, name = "content")
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

    @Column(name = "article_id", nullable = false)
    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
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

    @Column(nullable = false, name = "create_date")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}
