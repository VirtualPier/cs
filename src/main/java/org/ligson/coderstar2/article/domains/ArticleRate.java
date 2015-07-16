package org.ligson.coderstar2.article.domains;

import com.boful.common.date.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.hibernate.annotations.Type;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name="article_rate")
public class ArticleRate {
    private long id;
    private Article article;
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

    @Column(name = "article_id")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Column(name = "user_id")
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
