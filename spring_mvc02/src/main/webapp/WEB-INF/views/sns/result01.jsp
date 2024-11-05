<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
		<h2>카카오 로그인 성공</h2>
		<div id="result"></div>
		
		<!-- 계정과 함께 로그아웃 -->
		<a href="https://kauth.kakao.com/oauth/logout?client_id=f6f9a424847481c0f8290671bfb6b3ec&logout_redirect_uri=http://localhost:8080/kakaologout">
		로그아웃</a>

		<script type="text/javascript">
				$(function(){
					$("#result").empty();
					$.ajax({
						url : "/kakaoUserInfo",
						method : "post",
						dataType: "json",
						success : function(data) {
							// json 이용
							const name = data.properties.nickname;
							const img = data.properties.profile_image;
							const email = data.kakao_account.email;
							let str = "<li>닉네임 : " + name +"</li>";
							str += "<li>이메일 : " + email +"</li>";
							str += "<img src = " + img + ">";
							$("#result").append(str);
						},
						error : function() {
							alert("읽기 실패");
						}
					});
				});
		</script>
</body>
</html>