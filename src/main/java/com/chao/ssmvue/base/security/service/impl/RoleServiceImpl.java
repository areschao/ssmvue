package com.chao.ssmvue.base.security.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chao.ssmvue.base.security.entity.Role;
import com.chao.ssmvue.base.security.entity.UserRole;
import com.chao.ssmvue.base.security.mapper.RoleMapper;
import com.chao.ssmvue.base.security.service.RoleService;
import com.chao.ssmvue.base.security.service.UserRoleService;
import com.chao.ssmvue.core.entity.HttpResult;
import com.chao.ssmvue.core.service.MyServiceImpl;
import com.chao.ssmvue.core.utils.HttpResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LuZichao
 * @since 2018-11-19
 */
@Service
public class RoleServiceImpl extends MyServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<Role> selectByUserId(Long userId) {
        List<UserRole> userRoles = userRoleService.findRolesByUserId(userId);
        return findRoleByUserRoleList(userRoles);
    }

    @Override
    public HttpResult insertOrUpdate(Role role) {
        if (role.getId() == null) {
            save(role);
            return HttpResultUtil.createHttpResultSuccess("保存角色[" + role.getName() + "]成功");
        } else {
            updateById(role);
            return HttpResultUtil.createHttpResultSuccess("修改角色[" + role.getName() + "]成功");
        }
    }

    private List<Role> findRoleByUserRoleList(List<UserRole> userRoleList){
        if (CollectionUtils.isEmpty(userRoleList)){
            return new ArrayList<>();
        }
        Long[] collect = userRoleList.stream().map(UserRole::getRoleId).toArray(Long[]::new);
        return list(Wrappers.<Role>query().in(Role.ID, Arrays.asList(collect)));
    }
}
