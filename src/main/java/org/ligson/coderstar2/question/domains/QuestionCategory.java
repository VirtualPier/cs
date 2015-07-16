package org.ligson.coderstar2.question.domains;

import org.ligson.coderstar2.system.domains.Category;

import javax.persistence.*;

/**
 * Created by ligson on 2015/7/16.
 * 问题分类对应
 */
@Entity
@Table(name = "question_categroy")
public class QuestionCategory {
    private long id;
    private Question question;
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Column(nullable = false, name = "question_id")
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Column(nullable = false, name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
