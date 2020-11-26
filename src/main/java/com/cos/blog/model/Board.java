package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length = 100)
	private String title;
	
	@Lob
	private String content; //썸머노트 라이브러리 html태그가 섞여서 디자인됨
	
	@ColumnDefault("0")
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER) //many = board , one = user
	@JoinColumn(name = "userid") //userid 라는 이름으로 컬럼을 만들어줌
	private User user; //DB는 오브젝트를 저장못하는데 자바는 가능하다 FK
	
	//one = board, many = reply! 
	//@JoinColumn(name = "replyid") 해당부분 필요없음 ! user가 1개부터 여러개의 리플을 달수가있다!
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다! (FK가 아님)  DB에 컬럼을 따로 만들지말라고 요청함!
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
