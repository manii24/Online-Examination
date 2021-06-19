<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">

		<li class="breadcrumb-item active" aria-current="page">Start Exam</li>
	</ol>
</nav>

<div col-md-5img-thumbnail">
	<div id="feedback">
		<div class="container">
			<div class="col-md-5">
				<div class="form-area">
					<sf:form method="post"
						action="${pageContext.request.contextPath}/ctl/startExam"
						modelAttribute="form">
						<br style="clear: both">
						<h3 style="margin-bottom: 15px; text-align: left:;">Start Exam</h3>
						<b><%@ include file="businessMessage.jsp"%></b>
						<div class="form-group">
							<s:bind path="examId">
								<label>Exam Name :</label>
								<sf:select class="form-control" path="${status.expression}">
									<sf:option value="-1" label="Select" />
									<sf:options itemLabel="examName" itemValue="id" items="${examList}" />
								</sf:select>
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>

						<input type="submit" name="operation"
							class="btn btn-primary pull-right" value="Submit">or<input
							type="submit" name="operation" class="btn btn-primary pull-right"
							value="Reset">
					</sf:form>
				</div>
			</div>
		</div>
	</div>
	<!--feedback-->
	<br>