package com.zh.learning.config;

import com.zh.learning.security.StatelessAuthcFilter;
import com.zh.learning.security.StatelessRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * shiro配置
 *
 * @author ydh
 */
@Configuration
public class ShiroConfig {

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // realm和sessionmanager配置
        manager.setRealm(getRealm());
        manager.setSessionManager(getSessionManager());
        // 无状态session配置
        DefaultSubjectDAO dao = (DefaultSubjectDAO) manager.getSubjectDAO();
        DefaultSessionStorageEvaluator evaluator =
                (DefaultSessionStorageEvaluator) dao.getSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        return manager;
    }

    @Bean
    public SessionManager getSessionManager() {
        return new DefaultSessionManager();
    }

    @Bean
    public Realm getRealm() {
        return new StatelessRealm();
    }

     /**
     * 配置filter工厂
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        // 设置filter
        Map<String, Filter> filters = new HashMap<>(16);
        filters.put("statelessAuthc", new StatelessAuthcFilter());
        filterFactoryBean.setFilters(filters);
        // 配置拦截
        Map<String, String> chainMap = new HashMap<>(16);
        chainMap.put("/api/**", "statelessAuthc");
        chainMap.put("/anon/**", "anon");
        filterFactoryBean.setFilterChainDefinitionMap(chainMap);
        return filterFactoryBean;
    }

    /**
     * 加入注解的使用，不加入这个注解不生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
