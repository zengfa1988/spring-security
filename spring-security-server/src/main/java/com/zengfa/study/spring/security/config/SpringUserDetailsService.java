package com.zengfa.study.spring.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
public class SpringUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		if("admin".equals(userName)) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else if("zengfa".equals(userName)) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			roles.add(new SimpleGrantedAuthority("ROLE_DBA"));
		}else {
			return null;
		}
		UserDetails userDetails = new User(userName,"password",roles);
		return userDetails;
	}

}
