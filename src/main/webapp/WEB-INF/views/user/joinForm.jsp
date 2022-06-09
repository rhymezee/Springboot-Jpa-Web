<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">아이디</label>
			<input type="text" id="username" name="username" class="form-control" placeholder="Enter ID" required="required" maxlength="20" oninput="checkId()"/>
			<span class="id_ok pt-2">사용 가능한 아이디입니다.</span>
			<span class="id_already pt-2">이미 사용중인 아이디입니다.</span>
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label>
			<input type="password" id="password" class="form-control" placeholder="Enter Password" required="required"/>
		</div>
		<div class="form-group">
			<label for="characterName">캐릭터명</label>
			<input type="text" id="characterName" name="characterName" class="form-control" placeholder="Enter Character Name" required="required" maxlength="20"/>
		</div>
	</form>
	<button type="button" id="btn-join" class="btn btn-primary">회원가입</button>
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>