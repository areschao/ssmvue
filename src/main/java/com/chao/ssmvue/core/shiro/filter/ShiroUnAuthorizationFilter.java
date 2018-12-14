package com.chao.ssmvue.core.shiro.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LuZichao on  2018/12/11 8:58
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "shiroUnAuthorizationFilter")
public class ShiroUnAuthorizationFilter implements Filter {

    private FilterConfig config = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // response.setHeader( "Access-Control-Allow-Origin", config.getInitParameter( "AccessControlAllowOrigin" ) );
        //第二个参数填写允许跨域请求的 ip 加端口 因为不能填写 *
        response.setHeader( "Access-Control-Allow-Origin", "http://192.168.1.47:8081" );
        // 允许请求的方法
        response.setHeader( "Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT" );
        // 多少秒内，不需要再发送预检验请求，可以缓存该结果
        response.setHeader( "Access-Control-Max-Age", "3600" );
        // 表明它允许跨域请求包含xxx头
        response.setHeader( "Access-Control-Allow-Headers", "x-auth-token,Origin,Access-Token,X-Requested-With,Content-Type, Accept" );
        //是否允许浏览器携带用户身份信息（cookie）
        response.setHeader( "Access-Control-Allow-Credentials", "true" );
        // response.setHeader( "Access-Control-Expose-Headers", "*" );
        if (request.getMethod().toUpperCase().equals( "OPTIONS" )) {
            response.setStatus( 200 );
            return;
        }
        filterChain.doFilter( servletRequest, response );
    }

    @Override
    public void destroy() {
        this.config = null;
    }
}
