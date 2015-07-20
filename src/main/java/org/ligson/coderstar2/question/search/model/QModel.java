package org.ligson.coderstar2.question.search.model;

import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.user.domains.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2015/7/21.
 */
public class QModel {
    private long id;
    private String title;
    private User creator;
    private List<Category> categoryList = new ArrayList<Category>();
    private List<SysTag> sysTagList = new ArrayList<>();
    private int viewNum;
    private int replyNum;
    private int attentionNum;
    private String createDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<SysTag> getSysTagList() {
        return sysTagList;
    }

    public void setSysTagList(List<SysTag> sysTagList) {
        this.sysTagList = sysTagList;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public int getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(int attentionNum) {
        this.attentionNum = attentionNum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public QModel() {

    }

    public QModel(long id, String title, User creator, List<Category> categoryList, List<SysTag> sysTagList, int viewNum, int replyNum, int attentionNum, String createDate) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.categoryList = categoryList;
        this.sysTagList = sysTagList;
        this.viewNum = viewNum;
        this.replyNum = replyNum;
        this.attentionNum = attentionNum;
        this.createDate = createDate;
    }
}
