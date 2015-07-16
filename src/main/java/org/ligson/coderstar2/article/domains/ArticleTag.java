package org.ligson.coderstar2.article.domains;

import com.boful.common.date.utils.DateUtils;
import freemarker.template.utility.DateUtil;
import org.ligson.coderstar2.system.domains.SysTag;

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

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    public SysTag getTag() {
        return tag;
    }

    public void setTag(SysTag tag) {
        this.tag = tag;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
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
