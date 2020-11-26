package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //암호 원문
		String encPassword = encoder.encode(rawPassword); //해쉬화된 암호!
		user.setPassword(encPassword);
 		user.setRole(RoleType.USER);
			userRepository.save(user);

	}
	
	@Transactional
	public void 회원수정(User user) {
		//수정시에 영속성 컨텍스트에 User객체를 영속화 시키고 해당 User를 변경하면 더티체킹이 일어나서 변경된경우 자동으로 영속성 컨텍스트를
		//체크해서 db의 값을 변경시키는걸 이용함!
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기실패");
		});
		
		//oauth 사용자들은 비밀번호를 변경못하도록
		if (persistance.getOauth()==null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		
		
		

	}
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		//있을수도있고 없을수도 있음 
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}


}
