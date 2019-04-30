package com.zengfa.study.spring.security.github;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

//@Configuration
public class RestConfig {

	@Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
	
	@Bean
    public HttpHeaders getHttpHeaders() {
		// 要进行一个Http头信息配置
        HttpHeaders headers = new HttpHeaders();
        return headers;
	}
	
	public static void main(String[] args) {
		String clientId = "demoApp";
		String secret = "demoAppSecret";
		String auth = clientId+":"+secret;
        byte[] encodedAuth = Base64.encodeBase64((auth.getBytes(Charset.forName("US-ASCII")))); // 进行一个加密的处理
        // 在进行授权的头信息内容配置的时候加密的信息一定要与“Basic”之间有一个空格
        String authHeader = "Basic " + new String(encodedAuth);
        System.out.println(authHeader);
	}
}
