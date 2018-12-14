package com.chao.ssmvue.base.security.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chao.ssmvue.base.security.entity.Role;
import com.chao.ssmvue.base.security.entity.RoleMenu;
import com.chao.ssmvue.base.security.service.RoleMenuService;
import com.chao.ssmvue.base.security.service.RoleService;
import com.chao.ssmvue.core.controller.AdminController;
import com.chao.ssmvue.core.entity.HttpResult;
import com.chao.ssmvue.core.utils.HttpResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LuZichao
 * @since 2018-11-19
 */
@RestController
@RequestMapping("/role")
public class RoleController extends AdminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @GetMapping
    public HttpResult findAll() {
        return HttpResultUtil.createHttpResultSuccess("查询成功", roleService.list());
    }

    @RequiresPermissions("/role/add")
    @PostMapping
    public HttpResult saveOrUpdateUser(@Valid Role role, BindingResult bindingResult) {
        bindingResultValidate(bindingResult);
        return roleService.insertOrUpdate(role);
    }

    @RequiresPermissions("/role/delete")
    @DeleteMapping("{id}")
    public HttpResult deleteById(@PathVariable Long id) {
        return roleService.removeById(id) ? returnSuccess("删除成功") : returnSystemError("删除失败");
    }

    @RequiresPermissions("/role/saveMenus")
    @PostMapping("/saveMenu")
    public HttpResult saveMenuForRoleId(@RequestParam(value = "roleId") Long roleId,
                                        @RequestParam(value = "menuIdStr") String menuIdStr) {
        Assert.notNull(roleId, "角色id不能为空");
        Long[] longMenuIds = strToLongArray(menuIdStr, ",");
        roleMenuService.saveRoleMenus(roleId, longMenuIds);
        return returnSuccess("为角色Id[" + roleId + "],设置菜单[" + Arrays.toString(longMenuIds) + "]成功");
    }

    @GetMapping("menuIds/{roleId}")
    public HttpResult findMenuByRoleId(@PathVariable Long roleId) {
        return HttpResultUtil.createHttpResultSuccess(
                roleMenuService.listRoleMenuIds(roleId)
        );
    }

}

