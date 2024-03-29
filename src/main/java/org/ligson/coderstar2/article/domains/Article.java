package org.ligson.coderstar2.article.domains;

import com.boful.common.date.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name = "article")
public class Article {
    private long id;
    private Set<Remark> remarks = new HashSet<>();
    private Set<ArticleTag> tags = new HashSet<>();
    private Set<ArticleCategory> articleCategories = new HashSet<>();
    private String title;
    private String description;
    private String createDate = DateUtils.format();
    private User creator;
    private int state = 1;
    //回复量
    private long replyNum = 0;
    //浏览量
    private long viewNum = 0;
    //关注量
    private long attentionNum = 0;
    //海报
    private String poster;

    //管理员推荐数
    private int recommendNum = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(Set<Remark> remarks) {
        this.remarks = remarks;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    public Set<ArticleTag> getTags() {
        return tags;
    }

    public void setTags(Set<ArticleTag> tags) {
        this.tags = tags;
    }

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "article_id")
    public Set<ArticleCategory> getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(Set<ArticleCategory> articleCategories) {
        this.articleCategories = articleCategories;
    }

    @Column(name = "title", length = 255, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = true, name = "description")
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false, name = "create_date")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Column(name = "a_state", nullable = false)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Column(name = "reply_num", nullable = false)
    public long getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(long replyNum) {
        this.replyNum = replyNum;
    }

    @Column(name = "view_num", nullable = false)
    public long getViewNum() {
        return viewNum;
    }

    public void setViewNum(long viewNum) {
        this.viewNum = viewNum;
    }

    @Column(name = "attention_num", nullable = false)
    public long getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(long attentionNum) {
        this.attentionNum = attentionNum;
    }

    @Column(name = "poster", nullable = true)
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Column(name = "recommend_num", nullable = false)
    public int getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(int recommendNum) {
        this.recommendNum = recommendNum;
    }

    public static final Map<Integer, String> stateCnName = new HashMap<>();
    public static final int STATE_APPLY = 1;
    public static final int STATE_PUBLISH = 0;

    static {
        stateCnName.put(STATE_PUBLISH, "发布");
        stateCnName.put(STATE_APPLY, "审核");
    }

    public Article(String title, long id, String createDate, int state, long replyNum, long viewNum, long attentionNum, String poster, int recommendNum) {
        this.title = title;
        this.id = id;
        this.createDate = createDate;
        this.state = state;
        this.replyNum = replyNum;
        this.viewNum = viewNum;
        this.attentionNum = attentionNum;
        this.poster = poster;
        this.recommendNum = recommendNum;
    }

    public Article() {
    }
}
