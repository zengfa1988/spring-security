package com.zengfa.study.spring.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
//	public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";
//    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;
	
	protected SmsCodeAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}

	protected SmsCodeAuthenticationFilter() {
        //过滤的请求是什么
		super(new AntPathRequestMatcher("/login", "POST"));
		System.out.println(222);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		System.out.println(333);
		if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "不支持的请求方式: " + request.getMethod());
        }
		String userName = request.getParameter("userName");
		String password = request.getParameter("pwd");
		String code = request.getParameter("code");
		System.out.println(userName+"\t"+password+"\t"+code);
		
		SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(userName);
        setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	protected void setDetails(HttpServletRequest request,
            SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

}
