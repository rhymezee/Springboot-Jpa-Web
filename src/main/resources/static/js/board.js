let index = {
	init: function() {
		$("#btn-write").on("click", () => {
			this.write();
		});
	},
	
	write: function() {
		let data = {
			title : $("#title").val(),
			content : $("#content").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(response) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/board/index";
		}).fail(function(error) {
			console.log(JSON.stringify(error));
		});
	}
}

index.init();