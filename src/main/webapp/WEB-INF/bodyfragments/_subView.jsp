
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		<li class="breadcrumb-item active" aria-current="page">Get
			Subscription</li>
	</ol>
</nav>
<div class="container">
	<b><%@ include file="businessMessage.jsp"%></b>
	<div class="row justify-content-center">
		<div class="col-10">
			<div class="card">
				<h5 class="card-header">Subscription</h5>
				<div class="card-body">
					<h5 class="card-title">One Month Subscription</h5>
					<font color="green">200 Only</font>
					<p class="card-text">With supporting text below as a natural
						lead-in to additional content.</p>
						<sf:form method="post"
						action="${pageContext.request.contextPath}/ctl/subscription"
						modelAttribute="form">
						<input type="hidden" name="sub" value="1">
						<input type="submit" name="operation" value="Get Subscription">
					</sf:form>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="row justify-content-center">
		<div class="col-10">
			<div class="card">
				<h5 class="card-header">Subscription</h5>
				<div class="card-body">
					<h5 class="card-title">Three Month Subscription</h5>
					<font color="green">500 Only</font>
					<p class="card-text">With supporting text below as a natural
						lead-in to additional content.</p>
						<sf:form method="post"
						action="${pageContext.request.contextPath}/ctl/subscription"
						modelAttribute="form">
					<input type="hidden" name="sub" value="3">
					<input type="submit" name="operation" value="Get Subscription">
					</sf:form>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="row justify-content-center">
		<div class="col-10">
			<div class="card">
				<h5 class="card-header">Subscription</h5>
				<div class="card-body">
					<h5 class="card-title">Six Month Subscription</h5>
					<font color="green">600 Only</font>
					<p class="card-text">With supporting text below as a natural
						lead-in to additional content.</p>
						<sf:form method="post"
						action="${pageContext.request.contextPath}/ctl/subscription"
						modelAttribute="form">
					<input type="hidden" name="sub" value="6">
					<input type="submit" name="operation" value="Get Subscription">
					</sf:form>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="row justify-content-center">
		<div class="col-10">
			<div class="card">
				<h5 class="card-header">Subscription</h5>
				<div class="card-body">
					<h5 class="card-title">One Day Test Subscription</h5>
					<font color="green">Free</font>
					<p class="card-text">With supporting text below as a natural
						lead-in to additional content.</p>
						<sf:form method="post"
						action="${pageContext.request.contextPath}/ctl/subscription"
						modelAttribute="form">
					<input type="hidden" name="sub" value="2">
					<input type="submit" name="operation" value="Get Subscription">
					</sf:form>
				</div>
			</div>
		</div>
	</div>
	
</div>
<br>
<br>
