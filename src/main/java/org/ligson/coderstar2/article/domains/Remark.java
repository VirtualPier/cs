package org.ligson.coderstar2.article.domains;

import com.boful.common.date.utils.DateUtils;
import org.hibernate.annotations.Type;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name = "remark")
public class Remark {
    private long id;
    private String content;
    private User user;
    private Article article;
    //支持
    private int supportNum;
    //反对
    private int opposeNum;
    private String createDate = DateUtils.format();

    private Set<RemarkRate> rates = new HashSet<>();

    private Set<RemarkReply> replyList = new HashSet<>();

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

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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

    @OneToMany(mappedBy = "remark", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<RemarkRate> getRates() {
        return rates;
    }

    public void setRates(Set<RemarkRate> rates) {
        this.rates = rates;
    }

    @OneToMany(mappedBy = "remark", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<RemarkReply> getReplyList() {
        return replyList;
    }


    public void setReplyList(Set<RemarkReply> replyList) {
        this.replyList = replyList;
    }
}
