package com.chao.ssmvue.base.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.ssmvue.base.security.entity.Menu;
import com.chao.ssmvue.base.security.entity.Role;
import com.chao.ssmvue.core.entity.HttpResult;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LuZichao
 * @since 2018-11-19
 */
public interface MenuService extends IService<Menu> {

    List<Menu> selectMenusByRoleIds(Long[] roleIds);

    List<Menu> selectPermissionsByRoleIds(Long[] roleIds);

    List<Menu> selectAllMenusByRoleIds(Long[] roleIds);

    HttpResult insertOrUpdate(Menu menu);

    List<Menu> findAll();

}
