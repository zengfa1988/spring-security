package com.zengfa.study.spring.security.oath2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
public class MyUserService2 implements UserDetailsService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("spring".equals(username)) {
			//这里一定要加权限，权限名可用随便定义
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority("admin"));
			//密码可用不用加密,如果要加密，就要配置PasswordEncoder加密的bean,在WebSecurityConfigurer里配置
			String password = "1234567";
			password = passwordEncoder.encode(password);
			return new User(username, password, roles);
		}else {
			throw new UsernameNotFoundException("用户名"+username+"没有找到");
		}
	}

}
