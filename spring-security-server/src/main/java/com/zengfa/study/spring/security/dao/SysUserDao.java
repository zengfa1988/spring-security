package com.zengfa.study.spring.security.dao;

import java.util.List;

import com.zengfa.study.spring.security.po.SysUser;

public interface SysUserDao {

	public List<SysUser> findByUsername(String userName);
}
