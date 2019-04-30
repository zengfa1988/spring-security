package com.zengfa.study.spring.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

//@Component
public class SpringAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication, "非验证类型");
		//账号
		String userName = authentication.getName();
		//密码
		String password = authentication.getCredentials().toString();
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		if(userDetails == null) {
			throw new UsernameNotFoundException("用户名/密码无效");
		}
		String es = passwordEncoder.encode(password);
		boolean checkResult = passwordEncoder.matches("test", es);
		/*
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		if("admin".equals(userName)) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else if("zengfa".equals(userName)) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			roles.add(new SimpleGrantedAuthority("ROLE_DBA"));
		}else {
			throw new UsernameNotFoundException("用户名/密码无效");
		}
		*/
//		return new UsernamePasswordAuthenticationToken(userName, password,roles);
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), 
				userDetails.getPassword(),userDetails.getAuthorities());
		token.setDetails(userDetails);
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
}
