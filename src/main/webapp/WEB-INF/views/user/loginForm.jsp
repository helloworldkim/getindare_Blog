<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="email">Username</label>
		<input type="text" class="form-control" name="username" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label>
			<input type="password" class="form-control" name="password" placeholder="Enter password" id="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label">
			<input class="form-check-input" name="remember" type="checkbox"> Remember me
			</label>
		</div>
		<button type="submit" id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=a6597705c6a8f5f3eb33c2286dc18230&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code">
			<img height="38px" alt="로그인버튼" src="/image/kakao_login_button.png">
		</a>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>
