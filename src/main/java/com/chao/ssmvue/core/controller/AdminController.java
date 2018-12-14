package com.chao.ssmvue.core.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.ssmvue.base.security.entity.Menu;
import com.chao.ssmvue.base.security.entity.Role;
import com.chao.ssmvue.base.security.entity.User;
import com.chao.ssmvue.core.constant.PageCons;
import com.chao.ssmvue.core.entity.HttpResult;
import com.chao.ssmvue.core.exception.NotFoundAnyMenusException;
import com.chao.ssmvue.core.exception.NotFoundAnyRolesException;
import com.chao.ssmvue.core.exception.NotFoundCurrentUserException;
import com.chao.ssmvue.core.utils.HttpResultUtil;
import com.chao.ssmvue.core.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminController {

    protected final static String SESSION_CURRENT_USER = "currentUser";
    private final static String SESSION_CURRENT_ROLE = "currentRole";
    private final static String SESSION_CURRENT_MENU = "currentMenu";

    public void setCurrentUser(User user) {
        getHttpSession().setAttribute(SESSION_CURRENT_USER, user);
    }

    public User getCurrentUser() {
        Object obj = getHttpSession().getAttribute(SESSION_CURRENT_USER);
        if (obj == null) {
            throw new NotFoundCurrentUserException();
        }
        return (User) obj;
    }

    public void setCurrentRoles(List<Role> roleList) {
        getHttpSession().setAttribute(SESSION_CURRENT_ROLE, roleList);
    }

    @SuppressWarnings("unchecked")
    public List<Role> getCurrentRoles() {
        Object obj = getHttpSession().getAttribute(SESSION_CURRENT_ROLE);
        if (obj == null) {
            throw new NotFoundAnyRolesException();
        }
        return (List<Role>) obj;
    }

    public void setCurrentMenus(List<Menu> menuList) {
        getHttpSession().setAttribute(SESSION_CURRENT_MENU, menuList);
    }

    @SuppressWarnings("unchecked")
    public List<Menu> getCurrentMenus() {
        Object obj = getHttpSession().getAttribute(SESSION_CURRENT_MENU);
        if (obj == null) {
            throw new NotFoundAnyMenusException();
        }
        return (List<Menu>) obj;
    }

    private Session getHttpSession() {
        return SecurityUtils.getSubject().getSession();
    }

    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attrs != null;
        return attrs.getRequest();
    }

    protected HttpResult bindingResultValidate(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return returnValidationError(bindingResult.getFieldError().getDefaultMessage());
        }
        return null;
    }

    protected Long[] strToLongArray(String str , String sep){
        String[] strIds = str.split(sep);
        return StringUtil.stringArrayToLongArray(strIds);
    }

    protected HttpResult returnSuccess(String ocd) {
        return HttpResultUtil.createHttpResultSuccess(ocd);
    }

    protected HttpResult returnValidationError(String ocd) {
        return HttpResultUtil.createHttpResultValidationError(ocd);
    }

    protected HttpResult returnSystemError(String ocd) {
        return HttpResultUtil.createSystemError(ocd);
    }

    protected <T> Page<T> buildPage(ServletRequest request) {
        int pageNo = getPageNoByRequest(request);
        int pageSize = getPageSizeByRequest(request);
        return new Page<T>(pageNo, pageSize);
    }

    private int getPageNoByRequest(ServletRequest request) {
        try {
            return getParameterInt(request, PageCons.PAGE);
        } catch (NullPointerException e) {
            return PageCons.DEFAULT_PAGE;
        }
    }

    private int getPageSizeByRequest(ServletRequest request) {
        try {
            return getParameterInt(request, PageCons.ROWS);
        } catch (NullPointerException e) {
            return PageCons.DEFAULT_ROWS;
        }
    }

    private int getParameterInt(ServletRequest request, String name) {
        String value = request.getParameter(name);
        if (StringUtils.isNumeric(value)) {
            return Integer.parseInt(value);
        }
        throw new NullPointerException("未发现参数");
    }

}
