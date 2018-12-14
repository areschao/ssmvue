package com.chao.ssmvue.core.enums;

/**
 * Created by LuZichao on  2018/12/10 20:06
 */
public enum MenuType {

    菜单("1"),
    操作("2");

    private String value;

    MenuType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
