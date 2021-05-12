package com.hiekn.metaboot.rest;

import cn.hiboot.mcn.core.model.result.RestResp;
import com.hiekn.metaboot.base.CommonSearch;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.service.UserService;
import com.hiekn.metaboot.validator.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@Api(tags = "用户模块")
public class UserRestApi {

    @Autowired
    private UserService userService;

    @PostMapping("page")
    @ApiOperation("分页")
    public RestResp<List<UserBean>> listPage(@RequestBody CommonSearch commonSearch) {
        return userService.page(commonSearch);
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
    public RestResp<UserBean> add(@Valid UserBean userBean) {
        userService.save(userBean);
        return new RestResp<>(userBean);
    }

    @PutMapping("update/{id}")
    @ApiOperation("修改")
    public RestResp<?> update(@PathVariable String id,@Validated(Update.class) UserBean userBean) {
        userService.updateById(id,userBean);
        return new RestResp<>();
    }

}
