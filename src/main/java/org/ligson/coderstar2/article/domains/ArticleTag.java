package org.ligson.coderstar2.article.domains;

import com.boful.common.date.utils.DateUtils;
import freemarker.template.utility.DateUtil;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name = "article_tag")
public class ArticleTag {
    private long id;
    private SysTag tag;
    private Article article;
    private String createDate = DateUtils.format();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "tag_id")
    public SysTag getTag() {
        return tag;
    }

    public void setTag(SysTag tag) {
        this.tag = tag;
    }

    @Column(name = "article_id")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Column(nullable = false, name = "create_date")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
