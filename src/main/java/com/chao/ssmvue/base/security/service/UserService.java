package com.chao.ssmvue.base.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.ssmvue.base.security.entity.User;
import com.chao.ssmvue.core.entity.HttpResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LuZichao
 * @since 2018-11-19
 */
public interface UserService extends IService<User> {

    HttpResult insertOrUpdate(User user);

    User findByLoginNameAndPassword(String loginName, String password);

    void resetPassword(Long userId);

    void updatePassword(Long userId , String password);

}
