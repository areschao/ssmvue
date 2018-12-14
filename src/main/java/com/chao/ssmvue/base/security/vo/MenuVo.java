package com.chao.ssmvue.base.security.vo;

import com.chao.ssmvue.base.security.entity.Menu;
import com.chao.ssmvue.core.utils.TreeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@Data
@Accessors(chain = true)
public class MenuVo {

    private String id;

    private String icon;

    private String label;

    private String path;

    private List<MenuVo> subMenu = new ArrayList<>();

    public static List<MenuVo> formatMenuVoList(List<Menu> menuList) {
        List<Menu> treeList = TreeUtil.getTreeList("", menuList);
        return transFromMenuList(treeList);
    }

    private static MenuVo transFromMenuVo(Menu menu) {
        MenuVo menuVo = new MenuVo();
        menuVo.setId(String.valueOf(menu.getId()))
                .setIcon(menu.getIcon())
                .setLabel(menu.getName())
                .setPath(menu.getIndexUrl());
        if (menu.getChildList() != null) {
            menuVo.setSubMenu(transFromMenuList(menu.getChildList()));
        }
        return menuVo;
    }

    private static List<MenuVo> transFromMenuList(List<Menu> menuList) {
        return menuList.stream().map(MenuVo::transFromMenuVo).collect(Collectors.toList());
    }

}
