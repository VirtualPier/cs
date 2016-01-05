package org.ligson.coderstar2.article.domains;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name = "attention_article")
public class AttentionArticle implements Serializable {
    private long id;
    private long userId;
    private long articleId;
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

    @Column(nullable = false, name = "create_date")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


}
