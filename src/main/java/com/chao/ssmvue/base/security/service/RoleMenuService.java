package com.chao.ssmvue.base.security.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.ssmvue.base.security.entity.RoleMenu;

public interface RoleMenuService extends IService<RoleMenu> {

    void saveRoleMenus(Long roleId , Long[] menuIds);

    Long[] listRoleMenuIds(Long roleId);
}
