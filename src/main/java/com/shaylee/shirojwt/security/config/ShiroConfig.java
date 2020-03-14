package com.shaylee.shirojwt.security.config;

import com.shaylee.shirojwt.security.constant.SecurityConstant;
import com.shaylee.shirojwt.security.shiro.JwtFilter;
import com.shaylee.shirojwt.security.shiro.JwtRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: shiro 配置
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Configuration
public class ShiroConfig {

    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(JwtRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 使用APP端自定义的realm
        manager.setRealm(realm);
        // 关闭shiro session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        return manager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashMap<>();
        Map<String, String> filterRuleMap = new HashMap<>();
        // 添加APP端自定义的JWT Filter
        filterMap.put(SecurityConstant.SHIRO_FILTER_JWT, new JwtFilter());
        // 所有请求通过JWT Filter
        filterRuleMap.put("/**", SecurityConstant.SHIRO_FILTER_JWT);
        // 访问401和404页面不通过JWT Filter
        filterRuleMap.put("/401/**", "anon");
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setUnauthorizedUrl(SecurityConstant.SHIRO_FILTER_UNAUTHORIZED_URL);
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}