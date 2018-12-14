package com.chao.ssmvue.base.security.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.ssmvue.base.security.entity.User;
import com.chao.ssmvue.base.security.entity.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {

    List<UserRole> findRolesByUserId(Long userId);

    void saveUserRoles(Long userId , Long[] roleIds);

}
