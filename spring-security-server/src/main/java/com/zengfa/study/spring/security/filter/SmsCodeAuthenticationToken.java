package com.zengfa.study.spring.security.filter;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    //放我们的认证信息，验证起放手机号，验证后放用户信息
    private final Object principal;
    
	public SmsCodeAuthenticationToken(String mobile) {
		super(null);
        this.principal = mobile;
        setAuthenticated(false);
	}
	
	public SmsCodeAuthenticationToken(String mobile,Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
        this.principal = mobile;
        super.setAuthenticated(true);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}
	
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}
	
	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
	}

}
