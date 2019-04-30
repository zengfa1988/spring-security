package com.zengfa.study.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
public class Oauth2Controller {

	@Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeaders httpHeaders;
    
	@GetMapping(value = "/oauth/callback")
	public String callback(String code) {
//		String content = restTemplate.exchange(DEPT_GET_URL, HttpMethod.GET, 
//    			new HttpEntity<Object>(this.httpHeaders),String.class).getBody();
//    	return content;
//		
		
		return code;
	}
}
