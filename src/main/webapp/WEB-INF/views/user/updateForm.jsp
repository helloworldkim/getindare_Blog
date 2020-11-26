<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
	<input type="hidden" id="id" value="${principal.user.id}">
		<div class="form-group">
			<label for="email">Username</label>
			 <input type="text" value="${principal.user.username}" class="form-control" name="username" placeholder="Enter username" readonly id="username">
		</div>
		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">Password:</label> 
				<input type="password" class="form-control" name="password" placeholder="Enter password" id="password">
			</div>
		</c:if>
		<div class="form-group">
			<label for="email">Email</label> 
			<input type="email" value="${principal.user.email}" class="form-control" name="email" readonly placeholder="Enter email" id="email">
		</div>
	</form>
	<button type="button" id="btn-update" class="btn btn-primary">회원정보수정</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
