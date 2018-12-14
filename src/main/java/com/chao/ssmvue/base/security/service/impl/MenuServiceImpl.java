package com.chao.ssmvue.base.security.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chao.ssmvue.base.security.entity.Menu;
import com.chao.ssmvue.base.security.entity.RoleMenu;
import com.chao.ssmvue.base.security.mapper.MenuMapper;
import com.chao.ssmvue.base.security.service.MenuService;
import com.chao.ssmvue.base.security.service.RoleMenuService;
import com.chao.ssmvue.core.entity.HttpResult;
import com.chao.ssmvue.core.enums.MenuType;
import com.chao.ssmvue.core.service.MyServiceImpl;
import com.chao.ssmvue.core.utils.HttpResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LuZichao
 * @since 2018-11-19
 */
@Service
public class MenuServiceImpl extends MyServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<Menu> findAll() {
        List<Menu> menus = list();
        menus.forEach(t->{
            if (t.getParentId() != null){
                menus.forEach(v->{
                    if(v.getId().equals(t.getParentId())){
                        t.setParentName(v.getName());
                    }
                });
            }
        });
        return menus;
    }

    @Override
    public List<Menu> selectMenusByRoleIds(Long[] roleIds) {
        Long[] menuIds = getMenuIdsByRoleMenus(findRoleMenusByRoleIds(roleIds));
        return list(Wrappers.<Menu>query().eq(Menu.TYPE,MenuType.菜单.getValue()).in(Menu.ID, Arrays.asList(menuIds)).orderByAsc(Menu.SEQ));
    }

    @Override
    public List<Menu> selectPermissionsByRoleIds(Long[] roleIds) {
        Long[] menuIds = getMenuIdsByRoleMenus(findRoleMenusByRoleIds(roleIds));
        return list(Wrappers.<Menu>query().eq(Menu.TYPE,MenuType.操作.getValue()).in(Menu.ID, Arrays.asList(menuIds)));
    }

    @Override
    public List<Menu> selectAllMenusByRoleIds(Long[] roleIds) {
        Long[] menuIds = getMenuIdsByRoleMenus(findRoleMenusByRoleIds(roleIds));
        return list(Wrappers.<Menu>query().in(Menu.ID, Arrays.asList(menuIds)));
    }

    private List<RoleMenu> findRoleMenusByRoleIds(Long roleIds[]){
        return roleMenuService.list(Wrappers.<RoleMenu>query().in(RoleMenu.ROLE_ID, Arrays.asList(roleIds)));
    }

    private Long[] getMenuIdsByRoleMenus(List<RoleMenu> roleMenus){
        return roleMenus.stream().map(RoleMenu::getMenuId).toArray(Long[]::new);
    }

    @Override
    public HttpResult insertOrUpdate(Menu menu) {
        if (menu.getId() == null) {
            save(menu);
            return HttpResultUtil.createHttpResultSuccess("保存菜单[" + menu.getName() + "]成功");
        } else {
            updateById(menu);
            return HttpResultUtil.createHttpResultSuccess("修改菜单[" + menu.getName() + "]成功");
        }
    }


}
