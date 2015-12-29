package org.ligson.coderstar2.question.domains;

import com.boful.common.date.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * 问题表
 * Created by ligson on 2015/7/16.
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {
    private long id;
    private String title;
    private String description;
    private String createDate = DateUtils.format();

    private long creatorId;
    private double money = 0.0;
    private int state = 1;

    //回复量
    private long replyNum = 0;
    //浏览量
    private long viewNum = 0;
    //关注量
    private long attentionNum = 0;
    private long rightAskId = -1;
    private String poster;
    private int recommendNum = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, name = "title")
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


    @Column(name = "money", nullable = false)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Column(name = "q_state", nullable = false)
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

    @Column(name = "creator_id")
    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    @Column(name = "right_ask_id", nullable = true)
    public long getRightAskId() {
        return rightAskId;
    }

    public void setRightAskId(long rightAskId) {
        this.rightAskId = rightAskId;
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

    public Question() {
    }

}
