package com.hiekn.metaboot.rest;

import cn.hiboot.mcn.autoconfigure.web.validator.Phone;
import cn.hiboot.mcn.core.model.result.RestResp;
import com.hiekn.metaboot.bean.po.UserBean;
import com.hiekn.metaboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/")
@Api(tags = "登陆模块")
public class LoginRestApi {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ApiOperation("登录")
    public RestResp<UserBean> login(@NotBlank @Phone @ApiParam("手机号") String mobile,
                                    @NotBlank @ApiParam("密码") String password){
        return new RestResp<>(userService.login(mobile,password));
    }

    @PostMapping("logout")
    @ApiOperation("登出")
    public RestResp<?> logout(){
        userService.logout();
        return new RestResp<>();
    }

}
