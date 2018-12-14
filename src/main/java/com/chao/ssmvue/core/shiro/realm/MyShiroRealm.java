package com.chao.ssmvue.core.shiro.realm;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chao.ssmvue.base.security.entity.Menu;
import com.chao.ssmvue.base.security.entity.Role;
import com.chao.ssmvue.base.security.entity.User;
import com.chao.ssmvue.base.security.service.MenuService;
import com.chao.ssmvue.base.security.service.RoleService;
import com.chao.ssmvue.base.security.service.UserService;
import com.chao.ssmvue.core.utils.StringUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * shiro自定义realm
 * Created by LuZichao on  2018/12/3 16:25
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 用来验证身份是否正确
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String name = (String) token.getPrincipal();
        User user = userService.getOne(Wrappers.<User>query().eq(User.LOGIN_NAME, name));
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                getName());
    }

    /**
     * 用来验证权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roles = roleService.selectByUserId(user.getId());
        info.setRoles(roles.stream().map(Role::getName).collect(Collectors.toSet()));
        Set<String> collect = menuService.selectPermissionsByRoleIds(
                roles.stream()
                        .map(Role::getId)
                        .toArray(Long[]::new))
                .stream()
                .filter(menu -> StringUtil.isNotBlank(menu.getIndexUrl()))
                .map(Menu::getIndexUrl)
                .collect(Collectors.toSet());
        info.setStringPermissions(collect);
        return info;
    }


}
