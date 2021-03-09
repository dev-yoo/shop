package com.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/**
* <pre>
* com.lotte.ndp.diquest.config
* |_ SecurityConfig.java
*
* 개요 :
* -----------------------------------------------------------------------
* 변경일                   작성자                           변경내용
* ----------- ------------------- ---------------------------------------
* 2020. 07. 16.   NHN다이퀘스트 유대성         최초 작성
* ----------------------------------------------------------------------- 
* <pre>
* @version : 1.0
* @author : 유대성
* @see : 
*/ 
@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter{
  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
            http.cors().and().csrf().disable();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers( "/v2/api-docs"
					            				, "/configuration/ui"
					            				, "/swagger-resources"
					            				, "/configuration/security"
					            				, "/swagger-ui.html"
					            				, "/webjars/**"
					            				, "/swagger/**");
    }
}
