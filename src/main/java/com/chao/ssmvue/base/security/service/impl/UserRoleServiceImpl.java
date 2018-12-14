package com.chao.ssmvue.base.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chao.ssmvue.base.security.entity.UserRole;
import com.chao.ssmvue.base.security.mapper.UserRoleMapper;
import com.chao.ssmvue.base.security.service.UserRoleService;
import com.chao.ssmvue.core.service.MyServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl extends MyServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    public List<UserRole> findRolesByUserId(Long userId) {
        return list(Wrappers.<UserRole>query().eq(UserRole.USER_ID,userId));
    }

    @Transactional
    @Override
    public void saveUserRoles(Long userId, Long[] roleIds) {
        deleteByUserId(userId);
        saveRoleIdsForUserId(userId,roleIds);
    }

    private void deleteByUserId(Long userId){
        remove(Wrappers.<UserRole>query().eq(UserRole.USER_ID,userId));
    }

    private void saveRoleIdsForUserId(Long userId,Long[] roleIds){
        List<UserRole> userRoleList = createUserRoleList(userId, roleIds);
        saveBatch(userRoleList);
    }

    private List<UserRole> createUserRoleList(Long userId, Long[] roleIds) {
        List<UserRole> userRoleList = new ArrayList<>();
        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        return userRoleList;
    }

}
