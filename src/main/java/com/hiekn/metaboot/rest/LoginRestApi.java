package com.hiekn.metaboot.rest;

import cn.hiboot.mcn.core.model.result.RestResp;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
@Api("登陆模块")
public class LoginRestApi {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ApiOperation("登录")
    public RestResp<UserBean> login(@NotBlank @Pattern(regexp="^\\d{11}$",message = "请填写正确的手机号") @ApiParam("手机号") String mobile,
                                    @NotBlank @ApiParam("密码") String password){
        userService.login(mobile,password);
        return new RestResp<>();
    }

    @PostMapping("logout")
    @ApiOperation("登出")
    public RestResp logout(){
        userService.logout();
        return new RestResp<>();
    }

}
