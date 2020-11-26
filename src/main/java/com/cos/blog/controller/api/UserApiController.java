package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = "/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password,email
		System.out.println("UserAPIController: save호출!");

		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

	}
	@PutMapping(value ="/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		System.out.println("UserAPIController: update호출!");
		userService.회원수정(user);
		
		//강제로 다시 로그인시켜서 세션에 등록하도록 함
		Authentication authentication = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
						);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}


}
