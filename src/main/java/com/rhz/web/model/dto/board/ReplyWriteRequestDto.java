package com.rhz.web.model.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyWriteRequestDto {
	
	private int userId;
	private int boardId;
	private String content;

}