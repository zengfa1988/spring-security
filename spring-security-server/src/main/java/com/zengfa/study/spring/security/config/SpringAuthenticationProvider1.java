package com.zengfa.study.spring.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

//@Component
public class SpringAuthenticationProvider1 implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication, "非验证类型");
		//账号
		String userName = authentication.getName();
		//密码
		String password = authentication.getCredentials().toString();
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		if("admin".equals(userName)) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else if("zengfa".equals(userName)) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			roles.add(new SimpleGrantedAuthority("ROLE_DBA"));
		}else {
			throw new UsernameNotFoundException("用户名/密码无效");
		}
		return new UsernamePasswordAuthenticationToken(userName, password,roles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
