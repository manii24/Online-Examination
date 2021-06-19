<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">

		<li class="breadcrumb-item active" aria-current="page">Add
			Question</li>
	</ol>
</nav>

<div col-md-5img-thumbnail">
	<div id="feedback">
		<div class="container">
			<div class="col-md-5">
				<div class="form-area">
					<sf:form method="post"
						action="${pageContext.request.contextPath}/ctl/question"
						modelAttribute="form">
						<br style="clear: both">
						<h3 style="margin-bottom: 15px; text-align: left:;">Add Question</h3>
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

						<div class="form-group">
							<s:bind path="questionName">
								<label>Question Name :</label>
								<sf:input path="${status.expression}" placeholder="Enter Question Name"
									class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>

						<div class="form-row">
							<div class="form-group col-md-6">
								<s:bind path="option1">
									<label>Option 1 :</label>
									<sf:input path="${status.expression}"
										placeholder="Enter Option 1" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>
							<div class="form-group col-md-6">
								<s:bind path="option2">
									<label>Option 2 :</label>
									<sf:input path="${status.expression}"
										placeholder="Enter Option 2" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>
						</div>
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<s:bind path="option3">
									<label>Option 3 :</label>
									<sf:input path="${status.expression}"
										placeholder="Enter Option 3" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>
							<div class="form-group col-md-6">
								<s:bind path="option4">
									<label>Option 4 :</label>
									<sf:input path="${status.expression}"
										placeholder="Enter Option 4" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>
						</div>
						
						<div class="form-group">
							<s:bind path="correctAns">
								<label>Correct Ans:</label>
								<sf:input path="${status.expression}" placeholder="Enter Correct Ans"
									class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>
						<input type="submit" name="operation"
							class="btn btn-primary pull-right" value="Save">or<input
							type="submit" name="operation" class="btn btn-primary pull-right"
							value="Reset">
					</sf:form>
				</div>
			</div>
		</div>
	</div>
	<!--feedback-->
	<br>