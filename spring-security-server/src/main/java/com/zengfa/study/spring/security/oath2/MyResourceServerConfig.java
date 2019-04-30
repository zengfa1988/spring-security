package com.zengfa.study.spring.security.oath2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * https://www.jianshu.com/p/1a0a5c92185e
 * WebSecurityConfigurerAdapter和ResourceServerConfigurerAdapter区别
 * @author Thinkpad
 *
 */
//@Configuration
//@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        .antMatchers("/v2/api-docs", "/actuator/**", "/login").permitAll()
        .anyRequest().authenticated();
	}

	
}
