let index = {
	init: function() {
		$("#btn-join").on("click", () => { // this를 binding 하기 위해서 화살표 함수 사용
			this.join();
		});
	},

	join: function() {
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			characterName : $("#characterName").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // let data는 javascript object이므로 json으로 변환해서 java에게 전송한다.
			contentType: "application/json; charset=utf-8", // 위 data는 http body 데이터이므로 MIME 타입 설정해줘야 함.
			dataType: "json" // 요청에 대한 응답이 왔을 때, json을 javascript object로 변환해서 받아준다. (생략해도 자동으로 javascript object로 변환해서 받아줌)
		}).done(function(response) {
			alert("회원가입이 완료되었습니다.");
			location.href = "/user/loginForm";
		}).fail(function(error) {
			if (error.responseJSON.data == null) {
				alert(error.responseJSON.message);
			}
			else {
				alert(JSON.stringify(error.responseJSON.data));
			}
		});
	}
}

index.init();

function checkId() {
	let username = $("#username").val();
	
	$.ajax({
		type: "POST",
		url: "/auth/username/check",
		data: {
			username : username
		},
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res => {
		if (res.data) {
			$(".id_already").css("display", "inline-block");
			$(".id_ok").css("display", "none");
			$("#btn-join").prop("disabled", true);
		}
		else {
			$(".id_ok").css("display", "inline-block");
			$(".id_already").css("display", "none");
			$("#btn-join").prop("disabled", false);
		}
	}).fail(error => {
		console.log(error);
	});
}

function update(userId, event) {
	event.preventDefault(); // form 태그 action을 막아줌.
	let data = $("#profileUpdate").serialize();

	$.ajax({
		type: "PUT",
		url: `/user/${userId}/update`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res => { // Http Status Code 200번대면 done
		alert("회원수정이 완료되었습니다.");
		location.href = "/user/updateForm";
	}).fail(error => { // Http Status Code 200번대가 아닐 때 fail
		if (error.responseJSON.data == null) {
			alert(error.responseJSON.message);
		}
		else {
			alert(JSON.stringify(error.responseJSON.data));
		}
	});
}

/*
let index = {
	init: function() {
		$("#btn-save").on("click", () => { // this를 binding 하기 위해서 화살표 함수 사용
			this.save();
		});
		
		$("#btn-login").on("click", () => {
			this.login();
		});
		
		$("#btn-update").on("click", () => {
			this.update();
		});
	},

	save: function() {
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			characterName : $("#characterName").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // let data는 javascript object이므로 json으로 변환해서 java에게 전송한다.
			contentType: "application/json; charset=utf-8", // 위 data는 http body 데이터이므로 MIME 타입 설정해줘야 함.
			dataType: "json" // 요청에 대한 응답이 왔을 때, json을 javascript object로 변환해서 받아준다. (생략해도 자동으로 javascript object로 변환해서 받아줌)
		}).done(function(response) {
			console.log(response);
			if (response.status === 500) {
				alert("회원가입에 실패했습니다.");
				location.href = "/auth/joinForm";
			}
			else {
				alert("회원가입이 완료되었습니다.");
				location.href = "/auth/loginForm";
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
	
	login: function() {
		let data = {
			username : $("#username").val(),
			password : $("#password").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(response) {
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
	
	update: function() {
		let data = {
			id : $("#id").val(),
			username : $("#username").val(),
			password : $("#password").val(),
			characterName : $("#characterName").val()
		};
		
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(response) {
			alert("회원수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init();
*/