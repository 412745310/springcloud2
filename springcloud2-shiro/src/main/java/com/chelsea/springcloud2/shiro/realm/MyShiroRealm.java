package com.chelsea.springcloud2.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyShiroRealm extends AuthorizingRealm {
//    @Autowired
//    private AdminUserService adminUserService;
//    @Autowired
//    private RoleService roleService;
//    @Autowired
//    private PermissionService permissionService;
    
    private static String name = "zhangsan";
    private static String password = "123456";
    private static List<String> role = new ArrayList<>();
    private static List<String> permissionValues = new ArrayList<>();
    
    static {
        role.add("role1");
        role.add("role2");
        role.add("role3");
        
        permissionValues.add("perm1");
        permissionValues.add("perm2");
        permissionValues.add("perm3");
    }

    // 鉴权时调用，例如界面有shiro标签
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 访问@RequirePermission注解的url时触发
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        String username = principals.toString();
//        AdminUser adminUser = adminUserService.selectByUsername(principals.toString());
        // 获得用户的角色，及权限进行绑定
//        List<Role> role = roleService.selectById(adminUser.getRoleId());
        // 其实这里也可以不要权限那个类了，直接用角色这个类来做鉴权，
        // 不过角色包含很多的权限，已经算是大家约定的了，所以下面还是查询权限然后放在AuthorizationInfo里
        simpleAuthorizationInfo.addRoles(role);
        // 查询权限
//        List<Permission> permissions = permissionService.selectByRoleId(adminUser.getRoleId());
        // 将权限具体值取出来组装成一个权限String的集合
//        List<String> permissionValues = permissions.stream().map(Permission::getValue).collect(Collectors.toList());
        // 将权限的String集合添加进AuthorizationInfo里，后面请求鉴权有用
        simpleAuthorizationInfo.addStringPermissions(permissionValues);
        return simpleAuthorizationInfo;
    }

    // 用户登录时调用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        log.info("用户：{} 正在登录...", username);
//        AdminUser adminUser = adminUserService.selectByUsername(username);
        // 如果用户不存在，则抛出未知用户的异常
//        if (adminUser == null)
//            throw new UnknownAccountException();
//        return new SimpleAuthenticationInfo(username, adminUser.getPassword(), getName());
        if (!name.equals(username)) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
