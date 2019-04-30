package com.zengfa.study.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zengfa.study.spring.security.dao.SysUserDao;
import com.zengfa.study.spring.security.po.SysUser;

//@Service
public class SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	public List<SysUser> findByUsername(String userName){
		return sysUserDao.findByUsername(userName);
	}
}
