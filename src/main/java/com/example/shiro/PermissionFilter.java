package com.example.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义权限过滤器
 * 需要使用到 工具类 ShiroFilterUtils
 *
 * <p>
 * Subject 是一个核心概念，用于表示当前操作的用户或系统主体
 * 主要作用和功能：
 *      身份验证（Authentication）
 *      授权（Authorization）
 *      会话管理（Session Management）
 *      操作执行（Execution）
 * <p>
 * */

public class PermissionFilter extends AccessControlFilter {
    /**
     * 方法用于判断用户是否有权限访问请求的 URI
     * 这个方法通常用于检查当前用户是否被允许访问特定的资源
     * */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //先判断权限状态
        Subject subject = getSubject(request,response);

        HttpServletRequest httpRequest = ((HttpServletRequest)request);
        String uri = httpRequest.getRequestURI(); //获取url
        String basePath = httpRequest.getContextPath();//获取基本路径

        if(null != uri && uri.startsWith(basePath)){
            uri = uri.replaceFirst(basePath, ""); //去除其中的基本路径部分
        }

        //suject 里面包含这个用户所有权限,uri是我们用户在界面请求的URI,如果匹配成功表示有权限.
        if(subject.isPermitted(uri)){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    /**
     * 未授权处理
     *      ：如果在用户没有访问权限，在 onAccessDenied 方法执行
     * */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        //方法将请求重定向到未授权页面
        WebUtils.issueRedirect(request, response, ShiroFilterUtils.UNAUTHORIZED);

        return Boolean.FALSE;
    }
}
