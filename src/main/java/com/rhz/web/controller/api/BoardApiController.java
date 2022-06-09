package com.rhz.web.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rhz.web.config.auth.PrincipalDetails;
import com.rhz.web.model.Board;
import com.rhz.web.model.dto.ResponseDto;
import com.rhz.web.model.dto.board.ReplyWriteRequestDto;
import com.rhz.web.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
	
	private final BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<String> write(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principal) {
		boardService.write(board, principal);
		return new ResponseDto<String>(HttpStatus.OK.value(), "글쓰기가 완료되었습니다.");
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<String> update(@PathVariable int id, @RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principal) {
		boardService.update(id, board, principal);
		return new ResponseDto<String>(HttpStatus.OK.value(), "글수정이 완료되었습니다.");
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<String> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principal) {
		boardService.delete(id, principal);
		return new ResponseDto<String>(HttpStatus.OK.value(), "삭제가 완료되었습니다.");
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<String> replyWrite(@RequestBody ReplyWriteRequestDto replyWriteRequestDto) {
		boardService.replyWrite(replyWriteRequestDto);
		return new ResponseDto<String>(HttpStatus.OK.value(), "댓글 작성이 완료되었습니다.");
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<String> replyDelete(@PathVariable int replyId, @AuthenticationPrincipal PrincipalDetails principal) {
		boardService.replyDelete(replyId, principal);
		return new ResponseDto<String>(HttpStatus.OK.value(), "댓글 삭제가 완료되었습니다.");
	}

}

/*
@PostMapping("/api/board/{boardId}/reply")
public ResponseDto<String> replyWrite(@RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal, @PathVariable int boardId) {
	boardService.replyWrite(principal, boardId, reply);
	return new ResponseDto<String>(HttpStatus.OK.value(), "댓글 작성이 완료되었습니다.");
}
*/