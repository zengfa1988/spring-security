package com.zengfa.study.spring.security.oath2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig3 extends AuthorizationServerConfigurerAdapter{

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("root")
			.secret("root")
			//添加了refresh_token后，密码和授权码模式返回的token里有refresh_token
			.authorizedGrantTypes("client_credentials","password","refresh_token","authorization_code");
	}
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
	
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		// 配置token获取和验证时的策略
//		security.tokenKeyAccess("permitAll()")
//			.checkTokenAccess("isAuthenticated()")
//			.allowFormAuthenticationForClients();
//	}
	
//	@Autowired
//	private DataSource dataSource;
	
	
}
