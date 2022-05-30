package com.rhz.web.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // Summer Note Library : <html> 태그가 섞여서 디자인 됨.
	
	@ColumnDefault("0") // ColumnDefautl에서 숫자는 ''을 안넣어줘도 한다.
	private int count;
	
	@JoinColumn(name = "userId") // userId로 컬럼이 만들어짐.
	// Board(Many) : User(One) 연관관계 -> FK로 만들어짐.
	@ManyToOne(fetch = FetchType.EAGER) // ManyToOne의 Default는 EAGER 전략 : Join 해서 User 정보 바로 가져옴. 
	private User user; // DB는 Object를 저장할 수 없어서 FK를 사용한다. -> Java는 Object를 바로 저장할 수 있다.
	
	// @JoinColumn(name = "replyId") 를 하게 되면 제1정규화가 깨진다.
	// Board(One) : Reply(Many) 연관관계
	// mappedBy : 연관관계의 주인이 아니다. (나는 FK가 아니다. -> DB에 컬럼 만들지마. -> Reply 테이블의 boardId가 FK다.)
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // OneToMany의 Default는 LAZY 전략 -> EAGER로 바꿔서 상세보기 시 바로 가져올 예정.
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys; // 1개의 게시글엔 N개의 댓글이 있을 수 있으니 List가 되어야 한다.
	
	@CreationTimestamp
	private Timestamp createDate;

}