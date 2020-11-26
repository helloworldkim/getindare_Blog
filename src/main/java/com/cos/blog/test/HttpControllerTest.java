package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

//사용자요청 -> 데이터 응답
@RestController
public class HttpControllerTest {

	@GetMapping(value = "/http/get")
	public String getTest() {
		
		return "get요청";
	}
	
	@PostMapping(value = "/http/post")
	public String postTest() {
		
		return "post요청";
	}
	
	@PutMapping(value = "/http/put")
	public String putTest() {
		
		return "put요청";
	}
	
	@DeleteMapping(value = "/http/delete")
	public String deleteTest() {
		
		return "delete요청";
	}
	
}
