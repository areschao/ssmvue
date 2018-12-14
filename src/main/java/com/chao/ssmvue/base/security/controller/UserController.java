package com.chao.ssmvue.base.security.controller;


import com.chao.ssmvue.base.security.entity.User;
import com.chao.ssmvue.base.security.service.RoleService;
import com.chao.ssmvue.base.security.service.UserRoleService;
import com.chao.ssmvue.base.security.service.UserService;
import com.chao.ssmvue.core.annotation.ApiDesc;
import com.chao.ssmvue.core.controller.AdminController;
import com.chao.ssmvue.core.entity.HttpResult;
import com.chao.ssmvue.core.utils.HttpResultUtil;
import com.chao.ssmvue.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * <p>
 * user前端控制器
 * </p>
 *
 * @author LuZichao
 * @since 2018-11-19
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    @ApiDesc("查看所有用户")
    @RequiresPermissions("/user/get")
    public HttpResult findAll(ServletRequest request) {
        return HttpResultUtil.createHttpResultSuccess("查询成功", userService.page(buildPage(request)));
    }

    @RequiresPermissions("/user/add")
    @PostMapping
    @ApiDesc("保存修改用户")
    public HttpResult saveOrUpdateUser(@Valid User user, BindingResult bindingResult) {
        bindingResultValidate(bindingResult);
        return userService.insertOrUpdate(user);
    }

    @RequiresPermissions("/user/delete")
    @DeleteMapping("{id}")
    @ApiDesc("删除用户")
    public HttpResult deleteById(@PathVariable Long id) {
        return userService.removeById(id) ? returnSuccess("删除成功") : returnSystemError("删除失败");
    }

    @RequiresPermissions("/user/resetPassword")
    @PostMapping("/resetPassword/{id}")
    @ApiDesc("重置密码")
    public HttpResult resetPassword(@PathVariable Long id) {
        log.info("进入重置密码");
        userService.resetPassword(id);
        return returnSuccess("重置用户[" + id + "]密码成功");
    }

    @GetMapping("/roleList/{id}")
    public HttpResult getUserRoleList(@PathVariable Long id) {
        return HttpResultUtil.createHttpResultSuccess("查询成功", roleService.selectByUserId(id));
    }

    @RequiresPermissions("/user/saveRoles")
    @PostMapping("/saveRoles")
    public HttpResult saveRolesForUser(@RequestParam(value = "userId") Long userId,
                                    @RequestParam(value = "roleIdStr") String roleIdStr) {
        Assert.notNull(userId, "用户id不能为空");
        Long[] longRoleIds = strToLongArray(roleIdStr, ",");
        userRoleService.saveUserRoles(userId, longRoleIds);
        return returnSuccess("设置用户[" + userId + "],角色[" + Arrays.toString(longRoleIds) + "]成功");
    }

}

