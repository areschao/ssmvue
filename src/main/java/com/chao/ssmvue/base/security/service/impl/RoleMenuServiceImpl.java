package com.chao.ssmvue.base.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chao.ssmvue.base.security.entity.Menu;
import com.chao.ssmvue.base.security.entity.RoleMenu;
import com.chao.ssmvue.base.security.mapper.RoleMenuMapper;
import com.chao.ssmvue.base.security.service.MenuService;
import com.chao.ssmvue.base.security.service.RoleMenuService;
import com.chao.ssmvue.core.service.MyServiceImpl;
import com.chao.ssmvue.core.utils.TreeUtil;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleMenuServiceImpl extends MyServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public void saveRoleMenus(Long roleId, Long[] menuIds) {
        deleteAllByRoleId(roleId);
        Long[] newMenuIds = parentIdFilter(menuIds);
        List<RoleMenu> roleMenuList = createRoleMenuList(roleId, menuIds);
        roleMenuList.forEach(this::save);
    }

    @Override
    public Long[] listRoleMenuIds(Long roleId) {

        List<Menu> menus = menuService.selectAllMenusByRoleIds(new Long[]{roleId});
        List<Menu> treeList = TreeUtil.getTreeList("", menus);
        List<Long> menuIds = new ArrayList<>();
        getNoChildMenus(menuIds,treeList);


        return menuIds.toArray(new Long[0]);
    }

    private void getNoChildMenus(List<Long> noChildMenus , List<Menu> menuList){
        menuList.forEach(t->{
            if (t.getChildList() == null){
                noChildMenus.add(t.getId());
            }else {
                getNoChildMenus(noChildMenus,t.getChildList());
            }
        });
    }


    private Long[] parentIdFilter(Long[] menuIds) {
        return menuService.listByIds(Arrays.asList(menuIds))
                .stream()
                .filter(t -> t.getParentId() != null || !("").equals(t.getIndexUrl()))
                .map(Menu::getId).toArray(Long[]::new);
    }

    private void deleteAllByRoleId(Long roleId) {
        remove(Wrappers.<RoleMenu>query().eq(RoleMenu.ROLE_ID, roleId));
    }

    private List<RoleMenu> createRoleMenuList(Long roleId, Long[] menuIds) {
        List<RoleMenu> roleMenuList = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuList.add(roleMenu);
        }
        return roleMenuList;
    }


}
