<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">아이디</label>
			<input type="text" class="form-control" id="username" name="username" placeholder="Enter ID" required="required"/>
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required="required"/>
		</div>
		<button class="btn btn-primary">로그인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>