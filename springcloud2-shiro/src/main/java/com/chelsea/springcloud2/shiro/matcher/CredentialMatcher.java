package com.chelsea.springcloud2.shiro.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 自定义密码校验器
 * 
 * @author shevchenko
 *
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        // 后台传入的密码
        String password = new String(uToken.getPassword());
        // 获取数据库存放的的密码，也就是realm的doGetAuthenticationInfo方法传递的密码
        String dbPassword = (String) info.getCredentials();
        return this.equals(password, dbPassword);
    }

}
