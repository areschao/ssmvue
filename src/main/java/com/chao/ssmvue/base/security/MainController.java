package com.chao.ssmvue.base.security;


import com.chao.ssmvue.base.security.entity.Menu;
import com.chao.ssmvue.base.security.entity.Role;
import com.chao.ssmvue.base.security.entity.User;
import com.chao.ssmvue.base.security.service.MenuService;
import com.chao.ssmvue.base.security.service.RoleService;
import com.chao.ssmvue.base.security.vo.LoginInfo;
import com.chao.ssmvue.base.security.vo.MenuVo;
import com.chao.ssmvue.core.controller.AdminController;
import com.chao.ssmvue.core.entity.HttpResult;
import com.chao.ssmvue.core.enums.HttpResultCode;
import com.chao.ssmvue.core.utils.HttpResultUtil;
import com.chao.ssmvue.core.utils.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController extends AdminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @GetMapping({"/admin","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/unauth")
    @ResponseBody
    public HttpResult unauth(){
        System.out.println("跳到了无权访问路径");
        System.out.println("跳到了无权访问路径");
        System.out.println("跳到了无权访问路径");
        System.out.println("跳到了无权访问路径");
        return HttpResultUtil.createSystemError("无权访问");
    }

    @PostMapping("login")
    @ResponseBody
    public HttpResult login(@RequestParam(value = "loginName") String loginName,
                            @RequestParam(value = "password") String password) {
        UsernamePasswordToken token;
        Subject subject;
        try {
            subject = SecurityUtils.getSubject();
            token = new UsernamePasswordToken(loginName, SecurityUtil.encodeMd5(password));
            subject.login(token);
        } catch (Exception e) {
            //TODO 这里可能会抛出 UnsupportedEncodingException 这是因为md5加密出现的异常
            e.printStackTrace();
            return HttpResultUtil.createHttpResult(HttpResultCode.UNKNOW_LOGINNAME_PASSWORD, "用户名或密码错误");
        }
        User user = (User) subject.getPrincipal();
        setUserInfoToSession(user);
        return HttpResultUtil.createHttpResultSuccess(user.getLoginName() + "登录成功！", getLoginInfo(user));
    }

    private LoginInfo getLoginInfo(User user){
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUser(user);
        loginInfo.setMenuVoList(MenuVo.formatMenuVoList(super.getCurrentMenus()));
        return loginInfo;
    }

    private void setUserInfoToSession(User user){
        super.setCurrentUser(user);
        this.setCurrentRoles();
        this.setCurrentMenus();
    }

    private void setCurrentMenus() {
        List<Role> currentRoles = getCurrentRoles();
        Long[] roleIds = getRoleIds(currentRoles);
        List<Menu> menus = menuService.selectMenusByRoleIds(roleIds);
        setCurrentMenus(menus);
    }

    private Long[] getRoleIds(List<Role> roleList) {
        return roleList.stream().map(Role::getId).toArray(Long[]::new);
    }

    private void setCurrentRoles() {
        User user = getCurrentUser();
        List<Role> roleList = roleService.selectByUserId(user.getId());
        if (roleList.isEmpty()) {
            currentUserIsRoleIsNullHandler();
        }
        super.setCurrentRoles(roleList);
    }

    private void currentUserIsRoleIsNullHandler() {
        throw new NullPointerException("当前用户没有设置角色");
    }

}
