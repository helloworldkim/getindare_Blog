package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController {
	@Value("${cos.key}")
	private String coskey;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/auth/joinForm")
	public String joinForm() {
	
		return "user/joinForm";
	}
	
	@GetMapping(value = "/auth/loginForm")
	public String loginForm() {
	
		return "user/loginForm";
	}
	@GetMapping(value ="/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
//	@ResponseBody
	@GetMapping(value = "/auth/kakao/callback")
	public String kakaoCallback(String code) {
		
		//post방식으로 key=value 쌍으로 데이터를 요청(카카오쪽으로)
//		HttpURLConnection
//		Retrofit2
//		OkHttp
//		RestTemplate
		RestTemplate rt = new RestTemplate();
		//httpheader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		//헤더 전달할 데이터지정
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		//body에 데이터담을객체 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "a6597705c6a8f5f3eb33c2286dc18230");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		System.out.println("kakao code값!:"+code);
		System.out.println("보낼 body값!:"+params.toString());
		//httpheader와 httpbody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params,headers);
//				new HttpEntity<>(headers,params); 반대순서로 들어가니까 body가 없다고 나와서 30분간 삽질함
		MultiValueMap<String, String> body = kakaoTokenRequest.getBody();
		System.out.println("바디데이터:"+body);
		System.out.println("kakaoTokenRequest:"+kakaoTokenRequest);
		//http 요청하기 -post방식으로 -그리고 response 변수의 응답받음
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token", //token 요청 주소
				HttpMethod.POST,//요청하는 방식
				kakaoTokenRequest,	//headers , body의 데이터
				String.class //응답받을 데이터타입
		);
		//gson , Json simple, objectmapper(jackson springboot에 기본으로 포함된 jackson 라이브러리 사용)
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken=null;
		try {
			//데이터 받을때 객체내 변수명이 안맞으면 오류나서 try catch문을 사용해야함
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("카카오 엑세스 토큰"+oauthToken.getAccess_token());
		///////////////////////// token 정보 조회를위해 다시 요청하는부분
		
		RestTemplate rt2 = new RestTemplate();
		//httpheader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		//헤더 전달할 데이터지정
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		//httpheader와 httpbody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);
		//http 요청하기 -post방식으로 -그리고 response 변수의 응답받음
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me", //token 요청 주소
				HttpMethod.POST,//요청하는 방식
				kakaoProfileRequest2,	//headers , body의 데이터
				String.class //응답받을 데이터타입
		);
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile=null;
		try {
			//데이터 받을때 객체내 변수명이 안맞으면 오류나서 try catch문을 사용해야함
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//User 오브젝트: username,password,email
		System.out.println("카카오 아이디(번호):"+kakaoProfile.getId());
		System.out.println("카카오 이메일:"+kakaoProfile.getKakao_account().getEmail());
		//중복방지를 위해 두개를 섞어서만듬
		System.out.println("블로그 서버 유저네임:"+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("블로그 서버 이메일:"+kakaoProfile.getKakao_account().getEmail());
		//따로 고객에게 비밀번호를 입력받지는 않지만 패스워드값을 안주는경우 이름만 가지고 로그인이 가능하게되어버림 그래서 임의값을 주자!
		System.out.println("블로그 서버 패스워드:"+coskey);
		//카카오 정보를 토태로 강제로 회원가입을 시켜서 고객등록을 진행함
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(coskey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		//가입자 혹은 비가입자 체크가 필요함!
		User originUser =userService.회원찾기(kakaoUser.getUsername());
		//가입이 이미 되어있는경우 회원가입 후 로그인처
		if(originUser.getUsername()==null) {
			System.out.println("기존회원이 아니기에 자동으로 회원가입을 진행합니다");
			userService.회원가입(kakaoUser);
			
		}
		//로그인처리
		//강제로 다시 로그인시켜서 세션에 등록하도록 함
		System.out.println("자동 로그인을 진행합니다");
		Authentication authentication = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),coskey)
						);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "redirect:/";
	}
}
