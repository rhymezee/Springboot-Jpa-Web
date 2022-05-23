package com.rhz.web.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@JoinColumn(name = "boardId")
	@ManyToOne // Reply(Many) : Board(One) 연관관계
	private Board board;
	
	@JoinColumn(name = "userId")
	@ManyToOne // Reply(Many) : User(One) 연관관계
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;

}