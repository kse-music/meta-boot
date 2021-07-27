package com.hiekn.metaboot.bean.param;

import cn.hiboot.mcn.autoconfigure.web.validator.Phone;
import cn.hiboot.mcn.autoconfigure.web.validator.group.DefaultCrud;
import com.hiekn.metaboot.validator.UserValidator;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * UserParam
 *
 * @author DingHao
 * @since 2021/7/27 10:25
 */
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

    private String gender;

    private String company;

    private String status;

}
