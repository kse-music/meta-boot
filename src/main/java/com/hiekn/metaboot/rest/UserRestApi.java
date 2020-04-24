package com.hiekn.metaboot.rest;

import cn.hiboot.mcn.core.model.result.RestResp;
import com.hiekn.metaboot.bean.UserBean;
import com.hiekn.metaboot.service.UserService;
import com.hiekn.metaboot.util.CommonUtils;
import com.hiekn.metaboot.validator.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "users",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api("用户模块")
public class UserRestApi {

    @Autowired
    private UserService userService;

    @GetMapping("page")
    @ApiOperation("分页")
    public RestResp<List<UserBean>> listPage(UserBean userBean) {
        return userService.listPage(userBean);
    }

    @GetMapping
    @ApiOperation("列表")
    public RestResp<List<UserBean>> list() {
        return new RestResp<>(userService.selectByCondition(null));
    }

    @GetMapping("{id}")
    @ApiOperation("获取")
    public RestResp<UserBean> get(@PathVariable String id) {
        userService.assertSelfData(id);
        return new RestResp<>(userService.getByPrimaryKey(id));
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除")
    public RestResp delete(@PathVariable String id) {
        userService.assertSelfData(id);
        userService.deleteByPrimaryKey(id);
        return new RestResp<>();
    }

    @PostMapping
    @ApiOperation("新增")
    public RestResp<UserBean> add(@Valid UserBean userBean) {
        userBean.setId(CommonUtils.getRandomUUID());
        userService.saveSelective(userBean);
        return new RestResp<>(userBean);
    }

    @PutMapping
    @ApiOperation("修改")
    public RestResp update(@Validated(Update.class) UserBean userBean) {
        userService.updateByPrimaryKeySelective(userBean);
        return new RestResp<>();
    }

}
