package com.hiekn.metaboot.bean.po;

import cn.hiboot.mcn.core.model.base.BaseBean;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Setter
@Getter
@DynamicInsert
@Entity
@Table(name = "tb_user")
public class UserBean extends BaseBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String nickname;

    private String email;

    private String gender;

    private String company;

    private String status;

}