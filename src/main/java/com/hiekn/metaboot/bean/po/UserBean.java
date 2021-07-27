package com.hiekn.metaboot.bean.po;

import cn.hiboot.mcn.core.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "tb_user")
@Entity
@Setter
@Getter
public class UserBean extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String mobile;

    private String nickname;

    private String password;

    private String email;

    private String gender;

    private String company;

    private String status;

}