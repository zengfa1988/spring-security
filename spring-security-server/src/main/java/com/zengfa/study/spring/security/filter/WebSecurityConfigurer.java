package com.zengfa.study.spring.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 继承了WebSecurityConfigurerAdapter后，就默认开启了base认证
 * @author Thinkpad
 *
 */
//@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			//web前缀的都不需要认证就可用访问
			.antMatchers("/web/**").permitAll()
			//其它认证才能访问
			.anyRequest().authenticated()
			//默认登入页面/login GET,是生成的页面
			.and().formLogin()
			//配置了loginPage,一定要配置permitAll()才能跳到登入页面
			.loginPage("/login").permitAll()
			.and().csrf().disable()
			.apply(smsCodeAuthenticationSecurityConfig);
	}

}
