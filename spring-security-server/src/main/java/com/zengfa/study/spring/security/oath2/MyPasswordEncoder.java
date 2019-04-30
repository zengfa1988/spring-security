package com.zengfa.study.spring.security.oath2;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.zengfa.study.spring.security.service.DigestUtils;

public class MyPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		String spassword = DigestUtils.sha1Hex(rawPassword.toString());
		return spassword;
	}

	/**
	 * 第一个参数:前端传过来的值
	 * 第二个参数:存在数据库中已加密的值
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if(encodedPassword == null || encodedPassword.length() == 0) {
			return false;
		}
		if(encodedPassword.equals(encode(rawPassword))) {
			return true;
		}
		return false;
	}
}
