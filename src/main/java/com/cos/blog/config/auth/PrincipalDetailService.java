package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service//빈등록
public class PrincipalDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	//스프링이 로그인 요청을 가로챌때 username, password 2개를 가로챔!
	//password는 알아서 하고 username으로 동일한 객체가 db가 있는지만 확인 후 return
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당사용자를 찾을수 없습니다:"+username);
				});
			return new PrincipalDetail(principal); //시큐리티 세션에 user정보가 저장이됨!
			//기본값: 아이디 = user, 패스워드 = console창에 뜨는 임시값!
	}

}
