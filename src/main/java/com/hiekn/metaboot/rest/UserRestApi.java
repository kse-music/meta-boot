package com.hiekn.metaboot.rest;

import cn.hiboot.mcn.autoconfigure.web.validator.group.DefaultCrud;
import cn.hiboot.mcn.core.model.result.RestResp;
import com.hiekn.metaboot.bean.base.CommonSearch;
import com.hiekn.metaboot.bean.param.UserParam;
import com.hiekn.metaboot.bean.po.UserBean;
import com.hiekn.metaboot.bean.struct.BeanStruct;
import com.hiekn.metaboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@Api(tags = "用户模块")
public class UserRestApi {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanStruct beanStruct;

    @PostMapping("page")
    @ApiOperation("分页")
    public RestResp<List<UserBean>> listPage(@RequestBody CommonSearch commonSearch) {
        UserBean userBean = new UserBean();
        userBean.setNickname(commonSearch.getName());
        return userService.page(userBean,commonSearch);
    }

    @GetMapping("get/{id}")
    @ApiOperation("获取")
    public RestResp<UserBean> get(@PathVariable String id) {
        return new RestResp<>(userService.getById(id));
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除")
    public RestResp<?> delete(@PathVariable String id) {
        userService.deleteById(id);
        return new RestResp<>();
    }

    @PostMapping("save")
    @ApiOperation("新增")
    public RestResp<UserBean> add(@Validated(DefaultCrud.Create.class) @RequestBody UserParam userParam) {
        UserBean userBean = beanStruct.toUserBean(userParam);
        userService.save(userBean);
        return new RestResp<>(userBean);
    }

    @PutMapping("update/{id}")
    @ApiOperation("修改")
    public RestResp<?> update(@PathVariable String id,@Validated(DefaultCrud.Update.class) UserParam userParam) {
        userService.updateById(id,userService.save(beanStruct.toUserBean(userParam)));
        return new RestResp<>();
    }

}
