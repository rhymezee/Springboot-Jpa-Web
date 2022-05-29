<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${principal.user.id}"/>
	
		<div class="form-group">
			<label for="username">아이디</label>
			<input value="${principal.user.username}" type="text" class="form-control" id="username" readonly/>
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label>
			<input type="password" class="form-control" id="password" required="required"/>
		</div>
		<div class="form-group">
			<label for="characterName">캐릭터명</label>
			<input value="${principal.user.characterName}" type="text" class="form-control" id="characterName"/>
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">회원수정 완료</button>
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>