package com.chao.ssmvue.base.security.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chao.ssmvue.base.security.entity.Menu;
import com.chao.ssmvue.base.security.service.MenuService;
import com.chao.ssmvue.base.security.vo.MenuVo;
import com.chao.ssmvue.core.controller.AdminController;
import com.chao.ssmvue.core.entity.HttpResult;
import com.chao.ssmvue.core.enums.MenuType;
import com.chao.ssmvue.core.utils.HttpResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LuZichao
 * @since 2018-11-19
 */

@RestController
@RequestMapping("/menu")
public class MenuController extends AdminController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public HttpResult findAll() {
        return HttpResultUtil.createHttpResultSuccess("查询成功", menuService.findAll());
    }

    @PostMapping
    public HttpResult saveOrUpdateUser(@Valid Menu menu, BindingResult bindingResult) {
        bindingResultValidate(bindingResult);
        return menuService.insertOrUpdate(menu);
    }

    @DeleteMapping("{id}")
    public HttpResult deleteById(@PathVariable Long id) {
        return menuService.removeById(id) ? returnSuccess("删除成功") : returnSystemError("删除失败");
    }

    @GetMapping("/menuVo")
    public HttpResult getMenuVoList(){
        return HttpResultUtil.createHttpResultSuccess(MenuVo.formatMenuVoList(menuService.list()));
    }



}

