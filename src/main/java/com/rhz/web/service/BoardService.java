package com.rhz.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rhz.web.config.auth.PrincipalDetail;
import com.rhz.web.model.Board;
import com.rhz.web.model.dto.ReplyWriteRequestDto;
import com.rhz.web.repository.BoardRepository;
import com.rhz.web.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	
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
	
	@Transactional 
	public void write(Board board, PrincipalDetail principal) {
		board.setUser(principal.getUser());
		boardRepository.save(board);
	}
	
	@Transactional
	public void update(int id, Board requestBoard, PrincipalDetail principal) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 수정할 수 없습니다.");
		});
		
		if (board.getUser().getId() != principal.getUser().getId()) {
			throw new IllegalStateException("본인이 작성한 글이 아닙니다.");
		}
		else {
			board.setTitle(requestBoard.getTitle());
			board.setContent(requestBoard.getContent());
		}
	}
	
	@Transactional
	public void delete(int id, PrincipalDetail principal) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 삭제할 수 없습니다.");
		});
		
		if (board.getUser().getId() != principal.getUser().getId()) {
			throw new IllegalStateException("본인이 작성한 글이 아닙니다.");
		}
		else {
			boardRepository.deleteById(id);
		}
	}
	
	@Transactional
	public void replyWrite(ReplyWriteRequestDto replyWriteRequestDto) {
		replyRepository.mSave(replyWriteRequestDto.getUserId(), replyWriteRequestDto.getBoardId(), replyWriteRequestDto.getContent());
	}
	
	@Transactional
	public void replyDelete(int replyId) {
		replyRepository.deleteById(replyId);
	}

}

/*
@Transactional
public void replyWrite(PrincipalDetail principal, int boardId, Reply requestReply) {
	requestReply.setUser(principal.getUser());
	
	Board board = boardRepository.findById(boardId).orElseThrow(() -> {
		return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
	});
	requestReply.setBoard(board);
	
	replyRepository.save(requestReply);
}

@Transactional
public void replyWrite(ReplyWriteRequestDto replyWriteRequestDto) {
	User user = userRepository.findById(replyWriteRequestDto.getUserId()).orElseThrow(() -> {
		return new IllegalArgumentException("회원을 찾을 수 없습니다.");
	});
	
	Board board = boardRepository.findById(replyWriteRequestDto.getBoardId()).orElseThrow(() -> {
		return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
	});

	Reply reply = Reply.builder()
		.user(user)
		.board(board)
		.content(replyWriteRequestDto.getContent())
		.build();
	
	replyRepository.save(reply);
}

@Transactional
public void replyWrite(ReplyWriteRequestDto replyWriteRequestDto) {
	User user = userRepository.findById(replyWriteRequestDto.getUserId()).orElseThrow(() -> {
		return new IllegalArgumentException("회원을 찾을 수 없습니다.");
	});
	
	Board board = boardRepository.findById(replyWriteRequestDto.getBoardId()).orElseThrow(() -> {
		return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
	});

	Reply reply = new Reply();
	reply.update(user, board, replyWriteRequestDto.getContent());
	
	replyRepository.save(reply);
}
*/