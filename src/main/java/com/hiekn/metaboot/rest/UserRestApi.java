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

import javax.validation.Valid;
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
    public RestResp<RestData<UserBean>> listByPage(@Valid @BeanParam PageModel pageModel,
                                                   @QueryParam("mobile")String mobile) {
        UserBean userBean = new UserBean();
        userBean.setPageNo(pageModel.getPageNo());
        userBean.setPageSize(pageModel.getPageSize());
        userBean.setMobile(mobile);
        return new RestResp<>(userService.listByPage(userBean));
    }

    @GET
    @Path("/get")
    @ApiOperation("获取")
    public RestResp<UserBean> get(@ApiParam(required = true)@QueryParam("id") String id) {
        return new RestResp<>(userService.getByPrimaryKey(id));
    }

    @GET
    @Path("/delete")
    @ApiOperation("删除")
    public RestResp delete(@ApiParam(required = true)@QueryParam("id") String id) {
        userService.deleteByPrimaryKey(id);
        return new RestResp<>();
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
    @Path("/update")
    @ApiOperation("修改")
    public RestResp update(@ApiParam(required = true)@QueryParam("id") String id,
                           @ApiParam(required = true)@FormParam("bean") String bean) {
        UserBean userBean = JsonUtils.fromJson(bean, UserBean.class);
        userBean.setId(id);
        userService.updateByPrimaryKeySelective(userBean);
        return new RestResp<>();
    }

    @POST
    @Path("/login")
    @ApiOperation("登录")
    public RestResp<UserBean> login(@ApiParam(value="手机号",required=true)@FormParam("mobile") String mobile,
                                    @ApiParam(value="密码",required=true)@FormParam("password") String password){
        return new RestResp<>(userService.login(mobile,password));
    }

    @GET
    @Path("/logout")
    @ApiOperation("登出")
    public RestResp<Object> logout(){
        userService.logout();
        return new RestResp<>();
    }

}
