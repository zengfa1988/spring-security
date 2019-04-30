package com.zengfa.study.spring.security.oath2;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zengfa.study.spring.security.po.SysUser;

public class SecurityUser extends SysUser implements UserDetails{

	private final Collection<? extends GrantedAuthority> authorities;
	
	public SecurityUser(SysUser user,Collection<? extends GrantedAuthority> authorities) {
		this.setId(user.getId());
		this.setUserName(user.getUserName());
		this.setPassword(user.getPassword());
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return this.getUserName();
	}

}
