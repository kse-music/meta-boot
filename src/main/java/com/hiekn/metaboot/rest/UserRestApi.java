package com.hiekn.metaboot.rest;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.boot.autoconfigure.base.model.result.RestResp;
import com.hiekn.boot.autoconfigure.base.util.JsonUtils;
import com.hiekn.boot.autoconfigure.web.model.PageModel;
import com.hiekn.boot.autoconfigure.web.util.BeanValidator;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.conf.aop.CheckData;
import com.hiekn.metaboot.service.UserService;
import com.hiekn.metaboot.util.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Api("用户模块")
public class UserRestApi {

    @Autowired
    private UserService userService;

    @GET
    @Path("page")
    @ApiOperation("分页")
    public RestResp<RestData<UserBean>> listPage(@Valid @BeanParam PageModel pageModel,
                                                 @QueryParam("mobile")String mobile) {
        UserBean userBean = new UserBean();
        userBean.setPageNo(pageModel.getPageNo());
        userBean.setPageSize(pageModel.getPageSize());
        userBean.setMobile(mobile);
        return new RestResp<>(userService.listByPage(userBean));
    }

    @GET
    @ApiOperation("列表")
    public RestResp<List<UserBean>> list() {
        return new RestResp<>(userService.selectByCondition(null));
    }

    @GET
    @Path("{id}")
    @CheckData
    @ApiOperation("获取")
    public RestResp<UserBean> get(@PathParam("id") String id) {
        return new RestResp<>(userService.getByPrimaryKey(id));
    }

    @DELETE
    @Path("{id}")
    @CheckData
    @ApiOperation("删除")
    public RestResp delete(@PathParam("id") String id) {
        userService.deleteByPrimaryKey(id);
        return new RestResp<>();
    }

    @POST
    @ApiOperation("新增")
    public RestResp<UserBean> add(@NotNull @FormParam("bean") String bean) {
        UserBean userBean = JsonUtils.fromJson(bean, UserBean.class);
        BeanValidator.validate(userBean);
        userBean.setId(CommonUtils.getRandomUUID());
        userService.saveSelective(userBean);
        return new RestResp<>(userBean);
    }

    @PUT
    @Path("{id}")
    @CheckData
    @ApiOperation("修改")
    public RestResp update(@PathParam("id") String id,
                           @NotNull @FormParam("bean") String bean) {
        UserBean userBean = JsonUtils.fromJson(bean, UserBean.class);
        userBean.setId(id);
        userService.updateByPrimaryKeySelective(userBean);
        return new RestResp<>();
    }

}
