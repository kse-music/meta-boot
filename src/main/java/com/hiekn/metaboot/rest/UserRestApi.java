package com.hiekn.metaboot.rest;

import cn.hiboot.mcn.autoconfigure.jpa.Specifications;
import cn.hiboot.mcn.autoconfigure.jpa.predicate.DateBetweenPredicate;
import cn.hiboot.mcn.autoconfigure.jpa.predicate.FieldLikePredicate;
import cn.hiboot.mcn.autoconfigure.validator.group.DefaultCrud;
import cn.hiboot.mcn.core.model.result.RestResp;
import com.hiekn.metaboot.bean.param.UserParam;
import com.hiekn.metaboot.bean.po.User;
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

/**
 * 用户模块
 */
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

    /**
     * 新增
     * @param userParam 用户参数
     */
    @PostMapping("save")
    @ApiOperation("新增")
    public RestResp<User> add(@Validated(DefaultCrud.Create.class) @RequestBody UserParam userParam) {
        User userBean = beanStruct.toUser(userParam);
        userService.save(userBean);
        return new RestResp<>(userBean);
    }

    /**
     * 删除
     * @param id 用户id
     */
    @PostMapping("delete/{id}")
    @ApiOperation("删除")
    public RestResp<?> delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return new RestResp<>();
    }

    /**
     * 修改
     * @param userParam 用户参数
     */
    @PostMapping("update/{id}")
    @ApiOperation("修改")
    public RestResp<?> update(@PathVariable Integer id,@Validated(DefaultCrud.Update.class) @RequestBody UserParam userParam) {
        userService.updateById(id,beanStruct.toUser(userParam));
        return new RestResp<>();
    }

    /**
     * 分页
     * @param userSearch 用户参数
     */
    @PostMapping("page")
    @ApiOperation("分页")
    public RestResp<List<User>> listPage(@RequestBody UserSearch userSearch) {
        User userBean = new User();
        userBean.setNickname(userSearch.getStatus());
        return userService.page(Specifications.withAnd(userBean
                ,new FieldLikePredicate<>("nickname", userSearch.getName())
                ,new DateBetweenPredicate<>("createAt", userSearch.getStarTime(), userSearch.getEndTime())),userSearch);
    }

    /**
     * 详情
     * @param id 用户id
     */
    @GetMapping("get/{id}")
    @ApiOperation("获取")
    public RestResp<User> get(@PathVariable Integer id) {
        return new RestResp<>(userService.getById(id));
    }

    /**
     * 上传文件
     * @param file 文件
     */
    @PostMapping("upload")
    public RestResp<String> upload(@RequestPart MultipartFile file) {
        return new RestResp<>(file.getOriginalFilename());
    }

}
