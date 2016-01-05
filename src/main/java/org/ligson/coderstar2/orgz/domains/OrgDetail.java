package org.ligson.coderstar2.orgz.domains;

import org.ligson.coderstar2.user.domains.User;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ligson on 2015/11/6.
 * 机构信息表
 */
public class OrgDetail implements Serializable {
    private long id;
    private String name;
    private User account;
    private String address;
    private String description;
    private String licenseImg;
    private String idCardNumber;
    private String idCardPhoto;
    private Date createDate = new Date();
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAccount() {
        return account;
    }

    public void setAccount(User account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLicenseImg() {
        return licenseImg;
    }

    public void setLicenseImg(String licenseImg) {
        this.licenseImg = licenseImg;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardPhoto() {
        return idCardPhoto;
    }

    public void setIdCardPhoto(String idCardPhoto) {
        this.idCardPhoto = idCardPhoto;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
