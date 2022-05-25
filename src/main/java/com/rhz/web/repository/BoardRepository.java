package com.rhz.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rhz.web.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	@Modifying
	@Query(value = "UPDATE board SET count = count + 1 WHERE id = ?1", nativeQuery = true)
	int boardCount(int id);
	
}