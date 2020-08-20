package com.hoyetec.api.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.savedrequest.NullRequestCache;

import com.hoyetec.api.vo.KeycloakTokenInfo;
/**
 * 
*****************************************************************************
*
*    Page/Class Name: Config.java
*              Title: annotation設定檔
*        Description: spring boot 設定檔
*          Copyright: Hoyetec
*            Company: Hoyetec
*        Create Date: 2019年8月23日
*      Last Modifier: Arc Liu
*   Last Modify Date: 2019年8月23日
*             Author: Arc Liu  
*
*****************************************************************************
 */
@Configuration
@ComponentScan(
        basePackageClasses = KeycloakSecurityComponents.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org.keycloak.adapters.springsecurity.management.HttpSessionManager"))
@EnableWebSecurity
public class Config extends KeycloakWebSecurityConfigurerAdapter {

    @Bean
    @Scope("prototype")
    public KeycloakTokenInfo keycloakTokenInfo() {
        return new KeycloakTokenInfo();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  
        KeycloakAuthenticationProvider keycloakAuthenticationProvider
         = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(
          new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }
    
    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
    
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(
          new SessionRegistryImpl());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
        .antMatchers("/protected*").hasRole("user").anyRequest().permitAll()
        .and().authorizeRequests()
        .antMatchers(HttpMethod.GET, "/public/**").permitAll()
        .antMatchers(HttpMethod.POST, "/public/**").permitAll()
//        .antMatchers(HttpMethod.GET, "/userkyc/**").permitAll()
//        .antMatchers(HttpMethod.POST, "/userkyc/**").permitAll()
	    .and().requestCache().requestCache(new NullRequestCache())
	    .and().cors().and().csrf().disable();
    }
}
