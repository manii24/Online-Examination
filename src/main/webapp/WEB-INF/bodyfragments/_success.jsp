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
		
		<li class="breadcrumb-item active" aria-current="page">Success Subscription</li>
	</ol>
	</nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="body">
		<div class="modal-dialog  orsform_modal-dialog" >
			<div class="modal-content">


				<div class="modal-heading">

					<h2 class="text-center" style="color: green">Success Subscription Detail</h2>


				</div>

				<hr>
				<% UserDTO dto=(UserDTO)session.getAttribute("user");%>

				<div class="modal-body">
				<sf:form method="post"
						action="#"
						modelAttribute="form">
						<div class="form-group">
							<label for="login">Name:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=dto.getfName().toUpperCase()+" "+dto.getLName().toUpperCase()%></h3>
								</span> 
							</div>
						</div>
						<div class="form-group">
							<label for="login">Subscription Date :-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=DataUtility.getDateString(dto.getSubStartDate())%></h3>
								</span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="login">Subscription End Date :-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=DataUtility.getDateString(dto.getSubEndDate())%></h3>
								</span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="login">Subscription Type:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=dto.getSubType()%></h3>
								</span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="login">Status :-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=dto.getStatus()%></h3>
								</span> 
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

	