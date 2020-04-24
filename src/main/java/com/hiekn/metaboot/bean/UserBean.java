package com.hiekn.metaboot.bean;

import cn.hiboot.mcn.core.model.BaseModel;
import com.hiekn.metaboot.validator.UserBeanValidator;
import com.hiekn.metaboot.validator.group.Insert;
import com.hiekn.metaboot.validator.group.Update;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@UserBeanValidator
@GroupSequence({Insert.class,UserBean.class})
public class UserBean extends BaseModel {

    @NotEmpty(groups = Update.class)
    private String id;

    @Pattern(regexp = "^\\d{11}$",message = "请填写正确的手机号",groups = Insert.class)
    @NotEmpty(groups = Insert.class)
    private String mobile;

    private String nickname;

    @NotBlank(message = "密码不能为空",groups = Insert.class)
    private String password;

    @Email
    private String email;

    private String gender;

    private String company;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

}