package org.ligson.coderstar2.question.domains;

import org.ligson.coderstar2.system.domains.Category;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ligson on 2015/7/16.
 * 问题分类对应
 */
@Entity
@Table(name = "question_category")
public class QuestionCategory implements Serializable {
    private long id;
    private long questionId;
    private long categoryId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Column(name = "question_id", nullable = false)
    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @Column(name = "category_id", nullable = false)
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
