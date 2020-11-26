<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<div class="form-group">
			<label for="email">Username</label>
			 <input type="text" class="form-control" name="username" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" name="password" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email</label> 
			<input type="email" class="form-control" name="email" placeholder="Enter email" id="email">
		</div>
	</form>
	<button type="button" id="btn-save" class="btn btn-primary">회원가입완료 </button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
