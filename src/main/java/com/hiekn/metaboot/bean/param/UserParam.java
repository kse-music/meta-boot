package com.hiekn.metaboot.bean.param;

import cn.hiboot.mcn.autoconfigure.web.validator.Phone;
import cn.hiboot.mcn.autoconfigure.web.validator.group.DefaultCrud;
import com.hiekn.metaboot.validator.UserSequenceProvider;
import com.hiekn.metaboot.validator.UserValidator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotEmpty(groups = DefaultCrud.Update.class)
    private String id;

    @Phone
    @NotEmpty(groups = DefaultCrud.Create.class)
    private String mobile;

    @NotEmpty(groups = DefaultCrud.Create.class)
    private String nickname;

    @NotBlank(message = "密码不能为空",groups = DefaultCrud.Create.class)
    private String password;

    @Email
    private String email;
    @NotNull
    private Integer type;

    @NotEmpty(message = "position不能为空" , groups = {TypeA.class})
    private String position;

    private String gender;

    @NotEmpty(message = "company不能为空" , groups = {TypeB.class})
    private String company;

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
