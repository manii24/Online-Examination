<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<style>
@import
	url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap')
	;

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box
}

body {
	background-color: #333
}

.container {
	background-color: #555;
	color: #ddd;
	border-radius: 10px;
	padding: 20px;
	font-family: 'Montserrat', sans-serif;
	max-width: 700px
}

.container>p {
	font-size: 32px
}

.question {
	width: 75%
}

.options {
	position: relative;
	padding-left: 40px
}

#options label {
	display: block;
	margin-bottom: 15px;
	font-size: 14px;
	cursor: pointer
}

.options input {
	opacity: 0
}

.checkmark {
	position: absolute;
	top: -1px;
	left: 0;
	height: 25px;
	width: 25px;
	background-color: #555;
	border: 1px solid #ddd;
	border-radius: 50%
}

.options input:checked ~.checkmark:after {
	display: block
}

.options .checkmark:after {
	content: "";
	width: 10px;
	height: 10px;
	display: block;
	background: white;
	position: absolute;
	top: 50%;
	left: 50%;
	border-radius: 50%;
	transform: translate(-50%, -50%) scale(0);
	transition: 300ms ease-in-out 0s
}

.options input[type="radio"]:checked ~.checkmark {
	background: #21bf73;
	transition: 300ms ease-in-out 0s
}

.options input[type="radio"]:checked ~.checkmark:after {
	transform: translate(-50%, -50%) scale(1)
}

.btn-primary {
	background-color: #555;
	color: #ddd;
	border: 1px solid #ddd
}

.btn-primary:hover {
	background-color: #21bf73;
	border: 1px solid #21bf73
}

.btn-success {
	padding: 5px 25px;
	background-color: #21bf73
}

@media ( max-width :576px) {
	.question {
		width: 100%;
		word-spacing: 2px
	}
}
</style>
<c:url var="addSearch" value="/ctl/question/search" />

<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		<li class="breadcrumb-item active" aria-current="page">Ans List</li>
	</ol>
</nav>

<b><%@ include file="businessMessage.jsp"%></b>
	<div class="container mt-sm-5 my-1" style="background-color: #000000;">
		<div class="question ml-sm-5 pl-sm-5 pt-2">
			<c:forEach items="${list}" var="ex" varStatus="exm">
				<div class="py-2 h5">
					<b>Q. <c:out value="${ex.pageNo}" /> : <c:out
							value="${ex.questionName}" /></b>
				</div>
				<div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3" id="options">
					<c:if test="${ex.myAns==ex.correctAns}">
					<label class="options" style="color: green">Your Ans : ${ex.myAns}</label>
					</c:if>
					<c:if test="${ex.myAns!=ex.correctAns}">
					<label class="options" style="color: red">Your Ans : ${ex.myAns}</label>
					</c:if>
					<label class="options">Correct Ans : ${ex.correctAns}</label> 
				
				</div>
			</c:forEach>
		</div>
		</div>

<br><br>
