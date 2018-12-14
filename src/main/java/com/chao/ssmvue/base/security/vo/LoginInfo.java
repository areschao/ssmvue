package com.chao.ssmvue.base.security.vo;

import com.chao.ssmvue.base.security.entity.User;

import java.util.List;

public class LoginInfo {

    private User user;
    private List<MenuVo> menuVoList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MenuVo> getMenuVoList() {
        return menuVoList;
    }

    public void setMenuVoList(List<MenuVo> menuVoList) {
        this.menuVoList = menuVoList;
    }




}
