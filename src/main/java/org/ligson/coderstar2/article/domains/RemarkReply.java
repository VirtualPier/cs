package org.ligson.coderstar2.article.domains;

import com.boful.common.date.utils.DateUtils;
import freemarker.template.utility.DateUtil;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name = "remark_reply")
public class RemarkReply implements Serializable {
    private long id;
    private long remarkId;
    private long userId;
    private long atUserId;
    private String createDate = DateUtils.format();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "remark_id", nullable = false)
    public long getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(long remarkId) {
        this.remarkId = remarkId;
    }

    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(nullable = false, name = "at_user_id")
    public long getAtUserId() {
        return atUserId;
    }

    public void setAtUserId(long atUserId) {
        this.atUserId = atUserId;
    }

    @Column(nullable = false, name = "create_date")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
