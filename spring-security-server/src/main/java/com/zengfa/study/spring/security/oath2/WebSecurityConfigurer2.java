package com.zengfa.study.spring.security.oath2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 继承了WebSecurityConfigurerAdapter后，就默认开启了base认证
 * @author Thinkpad
 *
 */
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfigurer2 extends WebSecurityConfigurerAdapter{

//	@Bean
//	public UserDetailsService myUserService() {
//		return new MyUserService();
//	}
	
	/**
	 * 如果在UserDetailsService里的密码进行了加密，就需要配置加密bean
	 * @return
	 */
	@Bean
    PasswordEncoder passwordEncoder(){
        return new MyPasswordEncoder();
    }
	
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
			;
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//	}
	
	/**
	 * 不需要配置，否则密码验证时报错
	 * Handler dispatch failed; nested exception is java.lang.StackOverflowError
	 */
//	@Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
	
}
