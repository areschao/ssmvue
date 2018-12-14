package com.chao.ssmvue.base.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chao.ssmvue.base.security.entity.User;
import com.chao.ssmvue.base.security.mapper.UserMapper;
import com.chao.ssmvue.base.security.service.UserService;
import com.chao.ssmvue.core.entity.HttpResult;
import com.chao.ssmvue.core.service.MyServiceImpl;
import com.chao.ssmvue.core.utils.HttpResultUtil;
import com.chao.ssmvue.core.utils.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LuZichao
 * @since 2018-11-19
 */
@Service
public class UserServiceImpl extends MyServiceImpl<UserMapper, User> implements UserService {
    @Override
    public HttpResult insertOrUpdate(User user) {
        setUserDefaultPassword(user);
        if (user.getId() == null) {
            save(user);
            return HttpResultUtil.createHttpResultSuccess("保存用户[" + user.getLoginName() + "]成功");
        } else {
            updateById(user);
            return HttpResultUtil.createHttpResultSuccess("修改用户[" + user.getLoginName() + "]成功");
        }
    }

    @Override
    public User findByLoginNameAndPassword(String loginName, String password) {
        password = encodeMd5Password(password);
        return getOne(Wrappers.<User>query()
                .eq(User.LOGIN_NAME, loginName)
                .eq(User.PASSWORD, password));
    }

    @Transactional
    @Override
    public void resetPassword(Long userId) {
        User user = getById(userId);
        setUserDefaultPassword(user);
        resetNewPassword(user);
    }

    @Transactional
    @Override
    public void updatePassword(Long userId, String password) {
        User user = getById(userId);
        user.setPassword(password);
        resetNewPassword(user);
    }

    private void resetNewPassword(User user){
        updateById(user);
    }

    private void setUserDefaultPassword(User user){
        //TODO 把默认密码放到配置文件
        String defaultPassword = "xx1111";
        String password = encodeMd5Password(defaultPassword);
        user.setPassword(password);
    }

    private String encodeMd5Password(String password){
        try {
            return SecurityUtil.encodeMd5(password);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
