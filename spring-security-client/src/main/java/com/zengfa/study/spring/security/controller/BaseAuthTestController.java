package com.zengfa.study.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BaseAuthTestController {

	public static final String DEPT_GET_URL = "http://127.0.0.1:8080/depts/dept";
	
	@Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeaders httpHeaders;
    
    @RequestMapping(value = "/baseAuth/test")
    public String test() {
    	
    	String content = restTemplate.exchange(DEPT_GET_URL, HttpMethod.GET, 
    			new HttpEntity<Object>(this.httpHeaders),String.class).getBody();
    	return content;
    }
}
