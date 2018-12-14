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
public interface RoleService extends IService<Role> {

    List<Role> selectByUserId(Long UserId);

    HttpResult insertOrUpdate(Role role);

}
