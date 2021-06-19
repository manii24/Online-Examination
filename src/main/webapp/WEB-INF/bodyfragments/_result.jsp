<%@page import="java.util.Date"%>
<%@page import="in.com.online.exam.util.DataUtility"%>
<%@page import="in.com.online.exam.dto.ResultDTO"%>
<%@page import="in.com.online.exam.dto.UserDTO"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
 <nav
		aria-label="breadcrumb" role="navigation"  >
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Result</li>
	</ol>
	</nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="body">
		<div class="modal-dialog  orsform_modal-dialog" >
			<div class="modal-content">


				<div class="modal-heading">

					<h2 class="text-center">Result</h2>


				</div>

				<hr>
				<% ResultDTO dto=(ResultDTO)session.getAttribute("rBean");%>

				<div class="modal-body">
				<sf:form method="post"
						action="#"
						modelAttribute="form">
						<div class="form-group">
							<label for="login">Name:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=dto.getUserName()%></h3>
								</span> 
							</div>
						</div>
						<div class="form-group">
							<label for="login">Exam Name:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=dto.getExaminationName() %></h3>
								</span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="login">Exam Date :-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=DataUtility.getDateString(new Date())%></h3>
								</span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="login">Question:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=dto.getSize()%></h3>
								</span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="login">Correct:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=dto.getCount()%></h3>
								</span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="login">Result:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=dto.getResult()%></h3>
								</span> 
							</div>
						</div>
						

				 		<div class="form-group">
							<div class="input-group">
								<a href="/Online-Examination-System/ctl/viewAns"  class="btn btn-success btn-lg">View Ans </a>
							</div>
						</div>


						

					</sf:form>
				</div>


			</div>
			

		</div>
		
				
		 <div  style="width: 100px; height: 258px; "></div>
</div>
</div> </div> <!--feedback-->
<br>

	