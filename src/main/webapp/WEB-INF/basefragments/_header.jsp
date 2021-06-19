<%@page import="in.com.online.exam.util.DataUtility"%>
<%@page import="java.util.Date"%>
<%@page import="in.com.online.exam.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light" style="padding: 15px">
	<b>Online Examination System</b>
</nav>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
		
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">

			<li class="nav-item active"><a class="nav-link"
				href="<c:url value="/welcome"/>">Home <span class="sr-only">(current)</span></a>
			</li>

			<%
				UserDTO userBean = (UserDTO) session.getAttribute("user");
				boolean userLoggedIn = userBean != null;
			%>
			<%
				if (userLoggedIn) {
			%>

			<%
				if (userBean.getRole_Id() == 1) {
			%>
			<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/exam"/>">Add Exam</a></li>
				<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/exam/search"/>">Exam List</a></li>
				<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/question"/>">Add Question</a></li>
				<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/result/search"/>">View Results</a></li>

						<%
				} else if (userBean.getRole_Id() == 2) {
			%>
				<%if(userBean.getStatus().equalsIgnoreCase("Approved")){ %>
				<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/exam/search"/>">Exam List</a></li>
				<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/startExam"/>">Start Exam</a></li>
				<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/result/search"/>">Result List</a></li>
				<%}else{ %>
				<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/subscription"/>">Subscription</a></li>
				<%} %>
			<%
				}
			%>
			<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/profile"/>">My Profile</a></li>
			<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/changepassword"/>">Change Password</a></li>
			<%
				}else {
			%>
			<%} %>
		</ul>


	</div>


	<ul class="nav justify-content-end">


		<%
			if (userLoggedIn) {
		%>
		<%if(userBean.getRole_Id()==2){ %>
		<%if(userBean.getStatus().equalsIgnoreCase("Approved")){%>
		<li class="nav-item"><a class="nav-link"
			href="#">Subscription Type(<%=userBean.getSubType()%>)(<%=DataUtility.getDateDays(DataUtility.getDateString1(new Date()),DataUtility.getDateString1(userBean.getSubEndDate()))%> Days left)</a></li>
		<%}}%>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/login"/>">Logout</a></li>
		<%
			} else {
		%>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/login"/>">Sign In</a></li>
		<li class="nav-item "><a class="nav-link"
			href="<c:url value="/signUp"/>">Sign Up</a></li>	
		<%
			}
		%>

	</ul>

</nav>

