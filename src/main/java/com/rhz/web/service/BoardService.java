package com.rhz.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rhz.web.model.Board;
import com.rhz.web.model.User;
import com.rhz.web.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;

	@Transactional 
	public void write(Board board, User user) {
		//board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> list(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board readMore(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 읽을 수 없습니다.");
		});
	}
	
	@Transactional
	public int boardCount(int id) {
		return boardRepository.boardCount(id);
	}

}