package com.hiekn.metaboot.rest;

import com.hiekn.boot.autoconfigure.base.model.result.RestResp;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller
@Produces(MediaType.APPLICATION_JSON)
@Api("登陆模块")
public class LoginRestApi {

    @Autowired
    private UserService userService;

    @POST
    @Path("login")
    @ApiOperation("登录")
    public RestResp<UserBean> login(@NotBlank @Pattern(regexp="^\\d{11}$",message = "请填写正确的手机号") @ApiParam("手机号")@FormParam("mobile") String mobile,
                                    @NotBlank @ApiParam("密码")@FormParam("password") String password){
        userService.login(mobile,password);
        return new RestResp<>();
    }

    @POST
    @Path("logout")
    @ApiOperation("登出")
    public RestResp<Object> logout(){
        userService.logout();
        return new RestResp<>();
    }

}
