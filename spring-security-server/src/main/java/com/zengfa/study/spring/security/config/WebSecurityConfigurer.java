package com.zengfa.study.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .antMatchers("/anonymous/**").hasRole("ANONYMOUS")
                .anyRequest().authenticated()
                //设置登陆页面
                //允许所有人进行访问此路径
                .and().formLogin().loginPage("/login").permitAll()
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
                //关闭csrf保护
                .and().csrf().disable();
        //开启csrf后,logout只能post提交
        //参考https://blog.csdn.net/jxchallenger/article/details/58643152
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
//	@Bean
//    public RoleHierarchyVoter roleHierarchyVoter(){
//        RoleHierarchyVoter voter = new RoleHierarchyVoter(roleHierarchy());
//        return voter;
//    }
	
	@Bean
	public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_DBA");
        return roleHierarchy;
    }
	
	
}
