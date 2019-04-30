package com.zengfa.study.spring.security.dao;

import java.util.List;

import com.zengfa.study.spring.security.po.SysPermission;

public interface SysPermissionDao {

	public List<SysPermission> findByUserId(Long userId);
}
