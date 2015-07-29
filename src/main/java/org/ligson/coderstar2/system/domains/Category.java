package org.ligson.coderstar2.system.domains;

import javax.persistence.*;

/**
 * 分类
 * Created by ligson on 2015/7/16.
 */
@Entity
@Table(name = "category")
public class Category {
    private long id;
    private String name;
    private String description;
    private int sortIndex = 0;
    private long questionNum = 0;
    private long articleNum = 0;
    private String poster;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "sort_index", nullable = false)
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    @Column(name = "question_num", nullable = false)
    public long getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(long questionNum) {
        this.questionNum = questionNum;
    }

    @Column(name = "article_num", nullable = false)
    public long getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(long articleNum) {
        this.articleNum = articleNum;
    }

    @Column(name = "poster", nullable = true)
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
