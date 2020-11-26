package com.cos.blog.model;

import lombok.Data;

//카카오 OAuth response 데이터를 담을 객체
@Data
public class OAuthToken {
	
	private String access_token;
	private String token_type;
	private String refresh_token;
	private int expires_in;
	private String scope;
	private int refresh_token_expires_in;

}
