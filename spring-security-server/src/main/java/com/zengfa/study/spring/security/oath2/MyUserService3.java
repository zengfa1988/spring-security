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

import com.zengfa.study.spring.security.dao.SysPermissionDao;
import com.zengfa.study.spring.security.dao.SysUserDao;
import com.zengfa.study.spring.security.po.SysPermission;
import com.zengfa.study.spring.security.po.SysUser;

//@Service
public class MyUserService3 implements UserDetailsService{

	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysPermissionDao sysPermissionDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SysUser> userList = sysUserDao.findByUsername(username);
		if(userList == null || userList.size()<1) {
			throw new UsernameNotFoundException("用户名"+username+"不存在");
		}
		SysUser sysUser = userList.get(0);
		
		//这里一定要加权限，权限名可用随便定义
		List<SysPermission> permissionList = sysPermissionDao.findByUserId(sysUser.getId());
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		for(SysPermission sysPermission : permissionList) {
			roles.add(new SimpleGrantedAuthority(sysPermission.getName()));
		}
		//密码可用不用加密,如果要加密，就要配置PasswordEncoder加密的bean,在WebSecurityConfigurer里配置
		String password = sysUser.getPassword();
		password = passwordEncoder.encode(password);
		return new User(username, password, roles);
	}

}
