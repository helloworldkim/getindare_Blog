package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;

//@Repository //생략가능하다
public interface BoardRepository extends JpaRepository<Board, Integer>{


	
}
