<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="#" method="post">
		<div class="form-group">
			<label for="username">아이디</label>
			<input type="text" class="form-control" id="username" name="username" placeholder="아이디를 입력해주세요.">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력해주세요.">
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>