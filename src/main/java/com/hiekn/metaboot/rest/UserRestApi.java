package com.hiekn.metaboot.rest;


import com.hiekn.boot.autoconfigure.base.model.PageModel;
import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.boot.autoconfigure.base.model.result.RestResp;
import com.hiekn.boot.autoconfigure.base.util.BeanValidator;
import com.hiekn.boot.autoconfigure.base.util.JsonUtils;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Controller
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Api("用户模块")
@ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "string", name = "Authorization",required = true)})
public class UserRestApi {

    @Autowired
    private UserService userService;


    @GET
    @Path("/list/page")
    @ApiOperation("分页")
    public RestResp<RestData<UserBean>> listByPage(@BeanParam PageModel pageModel) {
        return new RestResp<>(userService.listByPage(pageModel,null));
    }

    @GET
    @Path("/get")
    @ApiOperation("获取")
    public RestResp<UserBean> get(@ApiParam(required = true)@QueryParam("id") Integer id) {
        return new RestResp<>(userService.getByPrimaryKey(id));
    }

    @POST
    @Path("/add")
    @ApiOperation("新增")
    public RestResp<UserBean> add(@ApiParam(required = true)@FormParam("bean") String bean) {
        UserBean userBean = JsonUtils.fromJson(bean, UserBean.class);
        BeanValidator.validate(userBean);
        userService.save(userBean);
        return new RestResp<>(userBean);
    }

    @POST
    @Path("/login")
    @ApiOperation("登录")
    public RestResp<UserBean> login(@ApiParam(value="用户名/邮箱",required=true)@FormParam("username") String username,
                                    @ApiParam(value="密码",required=true)@FormParam("password") String password){
        return new RestResp<>(userService.login(username,password));
    }

    @GET
    @Path("/logout")
    @ApiOperation("登出")
    public RestResp<Object> logout(){
        userService.logout();
        return new RestResp<>();
    }

}
