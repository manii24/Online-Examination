<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>
<script type="text/javascript">
var count = 10;
var interval = setInterval(function(){
  document.getElementById('count').innerHTML=count;
  count--;
  if (count === 0){
    clearInterval(interval);
    document.getElementById("myForm").submit();
  }
}, 1000);

</script>
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
		<li class="breadcrumb-item active" aria-current="page">Questions</li>
	</ol>
</nav>

<b><%@ include file="businessMessage.jsp"%></b>
<sf:form id="myForm" action="${addSearch}" method="post" modelAttribute="form">




	<sf:input type="hidden" path="pageNo" />
	<sf:input type="hidden" path="pageSize" />
	<sf:input type="hidden" path="listsize" />
	<sf:input type="hidden" path="total" />
	<sf:input type="hidden" path="pagenosize" />

		<input type="hidden" name="count" value="${count}" />

	<div class="container mt-sm-5 my-1">
	<p style="color: red; font-size: 15px">Time : <font id="count" /></p>
		<div class="question ml-sm-5 pl-sm-5 pt-2">
			<c:forEach items="${list}" var="ex" varStatus="exm">
				<div class="py-2 h5">
					<b>Q. <c:out value="${form.pageNo}" /> : <c:out
							value="${ex.questionName}" /></b>
							
							<input type="hidden" name="question" value="${ex.questionName}">
				</div>
				<div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3" id="options">
					<label class="options">${ex.option1}<input
						type="radio" name="ans" value="${ex.option1}" > <span class="checkmark"></span>
					</label> 
					
					<label class="options">${ex.option2}<input
						type="radio" name="ans" value="${ex.option2}"> <span class="checkmark"></span>
					</label>
					
					 <label class="options">${ex.option3}<input
						type="radio" name="ans" value="${ex.option3}"> <span class="checkmark"></span>
					</label>
					
					 <label class="options">${ex.option4}<input type="radio"
						name="ans" value="${ex.option4}"> <span class="checkmark"></span>
					</label>
				</div>
				<input type="hidden" name="examName" value="${ex.examName}">
			</c:forEach>
		</div>
		<div class="d-flex align-items-center pt-3">
			<div id="prev">
				<input type="submit" name="operation"
					<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
					class="btn btn-primary" value="Previous">
			</div>
			<c:if test="${total == pagenosize  || listsize < pageSize   }">
			<div class="ml-auto mr-sm-5">
				<input type="submit" name="operation"
					class="btn btn-primary" value="Submit">
			</div>
			</c:if>
			<div class="ml-auto mr-sm-5">
				<input type="submit" name="operation"
					<c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
					class="btn btn-primary" value="Next">
			</div>
		</div>
	</div>

<br><br>

</sf:form>