package org.ligson.coderstar2.article.domains;

import com.boful.common.date.utils.DateUtils;
import freemarker.template.utility.DateUtil;
import org.ligson.coderstar2.user.domains.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name = "remark_reply")
public class RemarkReply {
    private long id;
    private Remark remark;
    private User user;
    private User atUser;
    private String createDate = DateUtils.format();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "remark_id")
    public Remark getRemark() {
        return remark;
    }

    public void setRemark(Remark remark) {
        this.remark = remark;
    }

    @Column(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = true,name = "at_user_id")
    public User getAtUser() {
        return atUser;
    }

    public void setAtUser(User atUser) {
        this.atUser = atUser;
    }

    @Column(nullable = false, name = "create_date")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
