package com.hiekn.metaboot.rest;

import cn.hiboot.mcn.autoconfigure.jpa.Specifications;
import cn.hiboot.mcn.autoconfigure.jpa.predicate.DateBetweenPredicate;
import cn.hiboot.mcn.autoconfigure.jpa.predicate.FieldLikePredicate;
import cn.hiboot.mcn.autoconfigure.validator.group.DefaultCrud;
import cn.hiboot.mcn.core.model.result.RestResp;
import com.hiekn.metaboot.bean.param.UserParam;
import com.hiekn.metaboot.bean.po.UserBean;
import com.hiekn.metaboot.bean.search.UserSearch;
import com.hiekn.metaboot.bean.struct.BeanStruct;
import com.hiekn.metaboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("user")
@Api(tags = "用户模块")
public class UserRestApi {

    @Autowired
    private BeanStruct beanStruct;

    private final UserService userService;

    public UserRestApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("save")
    @ApiOperation("新增")
    public RestResp<UserBean> add(@Validated(DefaultCrud.Create.class) @RequestBody UserParam userParam) {
        UserBean userBean = beanStruct.toUserBean(userParam);
        userService.save(userBean);
        return new RestResp<>(userBean);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除")
    public RestResp<?> delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return new RestResp<>();
    }

    @PutMapping("update/{id}")
    @ApiOperation("修改")
    public RestResp<?> update(@PathVariable Integer id,@Validated(DefaultCrud.Update.class) @RequestBody UserParam userParam) {
        userService.updateById(id,beanStruct.toUserBean(userParam));
        return new RestResp<>();
    }

    @PostMapping("page")
    @ApiOperation("分页")
    public RestResp<List<UserBean>> listPage(@RequestBody UserSearch userSearch) {
        UserBean userBean = new UserBean();
        userBean.setNickname(userSearch.getStatus());
        return userService.page(Specifications.and(userBean
                ,new FieldLikePredicate<>("nickname", userSearch.getName())
                ,new DateBetweenPredicate<>("createAt", userSearch.getStarTime(), userSearch.getEndTime())),userSearch);
    }

    @GetMapping("get/{id}")
    @ApiOperation("获取")
    public RestResp<UserBean> get(@PathVariable Integer id) {
        return new RestResp<>(userService.getById(id));
    }

    @PostMapping("upload")
    public RestResp<String> upload(@RequestPart MultipartFile file) {
        return new RestResp<>(file.getOriginalFilename());
    }

}
