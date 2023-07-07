package com.hiekn.metaboot.bean.param;

import cn.hiboot.mcn.autoconfigure.validator.annotation.Phone;
import cn.hiboot.mcn.autoconfigure.validator.group.DefaultCrud;
import com.hiekn.metaboot.validator.UserSequenceProvider;
import com.hiekn.metaboot.validator.UserValidator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * UserParam
 *
 * @author DingHao
 * @since 2021/7/27 10:25
 */

@GroupSequenceProvider(UserSequenceProvider.class)
@UserValidator
@Setter
@Getter
public class UserParam {

    /**
     * 用户名
     */
    @Phone
    @NotEmpty(groups = DefaultCrud.Create.class)
    private String username;

    /**
     * 昵称
     */
    @NotEmpty(groups = DefaultCrud.Create.class)
    private String nickname;

    /**
     * 邮箱
     */
    @Email
    private String email;

    /**
     * 类型
     */
    @Range(min = 1,max = 2)
    private Integer type;

    /**
     * 职位
     */
    @NotEmpty(message = "position不能为空" , groups = {TypeA.class})
    private String position;

    /**
     * 性别
     * @mock 男
     */
    private String gender;

    /**
     * 公司
     */
    @NotEmpty(message = "company不能为空" , groups = {TypeB.class})
    private String company;

    /**
     * 状态
     */
    private String status;

    /**
     * 分组A
     */
    public interface TypeA {

    }

    /**
     * 分组B
     */
    public interface TypeB {

    }

}
