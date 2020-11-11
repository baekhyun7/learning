package com.zh.learning.security;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义realm
 *
 * @author candy
 */
@Slf4j
@Service
public class StatelessRealm extends AuthorizingRealm {

//    @Autowired
//    private RedisService redisService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private MenuService menuService;

    @Override
    public boolean supports(AuthenticationToken token) {
        // 仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;
    }

    /**
     * 登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
//        StatelessToken statelessToken = (StatelessToken) token;
//        String uniqueToken = statelessToken.getUniqueToken();
//        log.debug("token = " + uniqueToken);
//        // 判断token是否合法
//        String customerId = TokenUtils.getUserIdByToken(uniqueToken);
//        String tokenFromRedis = redisService.getToken(customerId);
//        // 合法用户，则将token失效时间重置为一小时
//        if (!StringUtils.isEmpty(tokenFromRedis)) {
//            redisService.putToken(customerId, tokenFromRedis);
//            redisService.putUserInfo(redisService.getUserInfo(customerId));
//        }
//        return new SimpleAuthenticationInfo(uniqueToken, tokenFromRedis, getName());
            return null;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        // 根据uniqueToken查找角色，请根据需求实现
//        String uniqueToken = (String) principals.getPrimaryPrincipal();
//        // 从token获取customerId
//        String customerId = TokenUtils.getUserIdByToken(uniqueToken);
//        // 通过customerId查询角色
//        /*SysUserPo sysUserPo = new SysUserPo();
//        sysUserPo.setId(customerId);*/
//        UserPo sysUserPo = userService.getById(customerId);
//
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        if (sysUserPo != null) {
//            List<MenuPo> list = menuService.getMenuList(customerId);
//            for (MenuPo menuPo : list) {
//                if (StringUtils.isNotBlank(menuPo.getPermission())) {
//                    // 添加基于Permission的权限信息
//                    for (String permission :
//                            StringUtils.split(menuPo.getPermission(), ",")) {
//                        authorizationInfo.addStringPermission(permission);
//                    }
//                }
//            }
//        }
//        return authorizationInfo;
        return null;
    }

}
