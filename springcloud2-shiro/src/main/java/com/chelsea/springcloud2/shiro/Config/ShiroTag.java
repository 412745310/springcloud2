package com.chelsea.springcloud2.shiro.Config;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 自定义标签方法
 * 
 * @author shevchenko
 *
 */
@Component
public class ShiroTag {

    // 判断当前用户是否已经登录认证过
    public boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    // 获取当前用户的用户名
    public String getPrincipal() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

    // 判断用户是否有 xx 角色
    public boolean hasRole(String name) {
        return SecurityUtils.getSubject().hasRole(name);
    }

    // 判断用户是否有 xx 权限
    public boolean hasPermission(String name) {
        return !StringUtils.isEmpty(name) && SecurityUtils.getSubject().isPermitted(name);
    }

}
