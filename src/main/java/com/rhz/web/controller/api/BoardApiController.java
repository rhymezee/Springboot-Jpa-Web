package com.rhz.web.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rhz.web.config.auth.PrincipalDetail;
import com.rhz.web.model.Board;
import com.rhz.web.model.dto.ResponseDto;
import com.rhz.web.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
	
	private final BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<String> join(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.write(board, principal.getUser());
		return new ResponseDto<String>(HttpStatus.OK.value(), "글쓰기가 완료되었습니다.");
	}

}