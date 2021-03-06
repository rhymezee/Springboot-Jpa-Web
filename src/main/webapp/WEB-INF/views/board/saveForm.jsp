<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
	<form>
		<div class="form-group">
			<input type="text" class="form-control" id="title" placeholder="Enter Title"/>
		</div>
		<div class="form-group">
			<textarea class="form-control summernote" rows="5" id="content" placeholder="Enter Content"></textarea>
		</div>
	</form>
	<button id="btn-write" class="btn btn-primary">글쓰기</button>
</div>

<script>
	$('.summernote').summernote({
		tabsize: 2,
		height: 300
	});
</script>

<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp" %>