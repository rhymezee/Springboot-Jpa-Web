package com.rhz.web.model;

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

// @DynamicInsert // null 인 필드는 INSERT 시 제외 시켜줌.
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // ORM : Java Object를 테이블로 매핑해주는 기술
public class User {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라감 (Oracle : Sequence, MySql : Auto_Increment)
	private int id;
	
	@Column(unique = true, nullable = false, length = 20)
	private String username;
	
	@Column(nullable = false, length = 50)
	private String password;
	
	@Column(unique = true, nullable = false, length = 20)
	private String characterName;
	
	// @ColumnDefault("'user'") // ColumnDefautl에서 문자는 ''을 넣어줘야 한다.
	@Enumerated(EnumType.STRING) // DB에는 RoleType이라는 게 없기 때문에, 해당 컬럼은 스트링이라는 걸 알려줌.
	private RoleType role; // Enum으로 변경 예정
	
	@CreationTimestamp // INSERT가 될 때 시간이 자동으로 입력됨.
	private Timestamp createDate;

}