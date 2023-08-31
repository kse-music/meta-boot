package com.hiekn.metaboot.bean.po;

import cn.hiboot.mcn.core.model.base.BaseBean;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Setter
@Getter
@DynamicInsert
@Entity
@Table(name = "m_user")
public class User extends BaseBean {

    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private String gender;
    /**
     * 状态
     */
    private String status;

}