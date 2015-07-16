package org.ligson.coderstar2.question.domains;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.system.domains.SysTag;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ligson on 2015/7/16.
 */
@Entity
@Table(name = "question_tag")
public class QuestionTag {
    private long id;
    private SysTag tag;
    private Question question;
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
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Column(name = "create_date", nullable = false)
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
