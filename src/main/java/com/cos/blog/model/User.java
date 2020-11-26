package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> JAVA(OR 다른언어) Object->테이블로 매핑해준다

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@DynamicInsert //insert할때 null인 필드를 제외하고 추가시켜줌
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;// auto-increment or 시퀀스
	
	@Column(nullable = false, length = 30)
	private String username; //아이디
	
	@Column(nullable = false, length = 100) //암호화 때문에 길이길에 잡는다
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	 
	//@ColumnDefault("'user'") //default 값을 지정함 role 의경우에는 user를 기본권한으로 줌
	@Enumerated(EnumType.STRING) //DB에는 EnumType이 없기에 어떤타입인지 선언해줘야함
	private RoleType role; //enum을 쓰는게 좋다 //ADMIN,USER
	
	private String oauth;//kakao google
	
	@CreationTimestamp //시간자동입력
	private Timestamp createDate;
	
	
	

}
