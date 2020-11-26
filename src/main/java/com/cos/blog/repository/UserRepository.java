package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//@Repository //생략가능하다
public interface UserRepository extends JpaRepository<User, Integer>{

	
	//User findByUsernameAndPassword(String username,String password);
	java.util.Optional<User> findByUsername(String username);
	
}
