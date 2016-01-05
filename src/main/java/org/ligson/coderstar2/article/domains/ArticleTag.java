package org.ligson.coderstar2.article.domains;

import com.boful.common.date.utils.DateUtils;
import freemarker.template.utility.DateUtil;
import org.ligson.coderstar2.system.domains.SysTag;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name = "article_tag")
public class ArticleTag implements Serializable {
    private long id;
    private long tagId;
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

    @Column(nullable = false, name = "tag_id")
    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    @Column(nullable = false, name = "article_id")
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
