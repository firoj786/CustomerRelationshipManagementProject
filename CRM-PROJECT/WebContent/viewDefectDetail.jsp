<%@ page
	import="java.util.List, com.src.java.crm.dto.DesTable, com.src.java.crm.dto.Severity, com.src.java.crm.utilities.DefectStatusConstant,
	com.src.java.crm.dto.User, com.src.java.crm.dto.Comment,com.src.java.crm.dto.CrmRelease,com.src.java.crm.dto.CrmModule"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="common/links.html"%>

<%
	User user = (User) session.getAttribute("UserDetails");
	DesTable dt = (DesTable) request.getAttribute("DefectDetails");
%>
<body>

	<div id="wrapper">

		<jsp:include page="common/header.jsp" />

		<section id="inner-headline">
			<div class="container">
				<div class="row">
					<div class="span12">
						<div class="inner-heading">
							<ul class="breadcrumb">
								<li><a href="index.jsp">Home</a> <i
									class="icon-angle-right"></i></li>
								<li class="active">Defect</li>
							</ul>
							<h2>Defect Detailed History</h2>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="content">
			<div class="container">
				<div class="row">
					<div class="span12">
						<h2>
							<font color="red"><%=(request.getAttribute("eMsg") != null ? request.getAttribute("eMsg") : "")%></font>
							<font color="green"><%=(request.getAttribute("sMsg") != null ? request.getAttribute("sMsg") : "")%></font>
						</h2>
						<form name="raisdefectform" action="viewDefectList.do"
							method="post" role="form" class="contactForm"
							onsubmit="return validateForm()">
							<div class="row">
								<div class="span1 form-group field">
									<label>Defect Description</label>
								</div>
								<div class="span6 form-group">
									<textarea name="description" readonly><%=dt.getDesc()%></textarea>
									<div class="validation" onblur="defectdesc()"></div>
								</div>
								<div class="span1 form-group field">
									<label>Severity </label>
								</div>
								<div class="span4 form-group field">
									<select name="severity">
										<%
											List<Severity> severityList = (List<Severity>) application.getAttribute("SeverityList");
																											for (Severity s : severityList) {
										%>
										<option value="<%=s.getId()%>"
											<%=(dt.getSevId() == s.getId() ? "Selected" : "")%>><%=s.getDescription()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="span1 form-group field">
									<label>Step to Reproduce* </label>
								</div>
								<div class="span6 form-group">
									<textarea name="str" readonly><%=dt.getStrp()%></textarea>
									<div class="validation"></div>
								</div>
								<div class="span1 form-group field">
									<label>Assigned to </label>
								</div>
								<div class="span4 form-group field">
									<select name="assignedTo">
										<%
											List<User> userList = (List<User>) application.getAttribute("EmployeeAndSalesList");
																											userList.addAll((List<User>) application.getAttribute("CustomerList"));
																											for (User u : userList) {
										%>
										<option value="<%=u.getUserId()%>"
											<%=(dt.getAssignedTo() == u.getUserId() ? "Selected" : "")%>><%=u.getFname()%>&nbsp<%=u.getLname()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>

							<div class="row">
								<div class="span1 form-group field">
									<label>Release Version </label>
								</div>
								<div class="span3 form-group field">

									<%
										List<CrmRelease> simReleaseList = (List<CrmRelease>) application.getAttribute("GpsSimReleaseList");

																								for (CrmRelease g : simReleaseList) {
																									if (g.getId() == dt.getReleaseVersionId()) {
									%>
									<input type="text" name="releaseVersion"
										value="<%=g.getVersion()%>" readonly />
									<%
										}
																								}
									%>

								</div>

								<div class="span1 form-group field">
									<label>Module Name </label>
								</div>
								<div class="span3 form-group field">
									<%
										List<CrmModule> moduleList = (List<CrmModule>) application.getAttribute("GpsModuleList");

																								for (CrmModule m : moduleList) {
																									if (m.getId() == dt.getModuleNameId()) {
									%>
									<input type="text" name="modulename"
										value="<%=m.getDescription()%>" readonly />
									<%
										}
																	}
									%>
								</div>

								<div class="span1 form-group field">
									<label>Module Version </label>
								</div>
								<div class="span3 form-group field">
									<input type="text" name="moduleversion"
										value="<%=dt.getModuleVersionId()%>" readonly />
								</div>
							</div>
							<div class="row">
								<%
									if (user.getRoleId() != 2) {
								%>
								<div class="span1 form-group field">
									<label>Fixed In </label>
								</div>
								<div class="span3 form-group field">
									<select name="fixedinversion">
										<%
											simReleaseList = (List<CrmRelease>) application.getAttribute("GpsSimReleaseList");

																				for (CrmRelease g : simReleaseList) {
										%>
										<option value="<%=g.getId()%>"
											<%=g.getId() == dt.getReleaseVersionId() ? "Selected" : ""%>><%=g.getVersion()%></option>
										<%
											}
										%>
									</select>
								</div>
								<div class="span1 form-group field">
									<label>Status </label>
								</div>
								<div class="span3 form-group field">
									<select name="status">
										<%
											for (DefectStatusConstant dsc : DefectStatusConstant.values()) {
										%>
										<option value="<%=dsc.name()%>"
											<%=dt.getStatus().name().equals(dsc.name()) ? "Selected" : ""%>><%=dsc.name()%></option>
										<%
											}
										%>
									</select>
								</div>	
							</div>

							<%
								List<Comment> comments = dt.getComments();
								if (comments != null && comments.size() > 0) {
									for (Comment c : comments) {
							%>
								<div class="row">
								<div class="lab">
									<lable><b>Date:<%=c.getCommentedOn()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									Given By:<%=c.getCommentGivenByName()%></b>
									</lable>
									
								</div>
								<br>
								<div class="span11 form-group field">
									<textarea type="comment" name="comment" ><%=c.getDescription()%></textarea>									
								</div>
							  </div>

							<%
								}
								}
							%>
								<div class="row">
								<div class="lab">
								<label>Comment / Resolution </label>
									
								</div>
								<br>
								<div class="span8 form-group field">
									<textarea name="resolution" value="<%=dt.getResolution()%>"></textarea>
									<input type="hidden" name="defectId" value="<%=dt.getId()%>" />								
								</div>
							  </div>
								<%
									} else {
								%>
								<div class="row">
								<div class="lab">
								<label>Comment / Resolution </label>
									
								</div>
								<br>
								<div class="span8 form-group field">
									<textarea name="resolution" value="<%=dt.getResolution()%>"></textarea>
									<input type="hidden" name="defectId" value="<%=dt.getId()%>" />								
								</div>
							  </div>
								<%} %>

							<%
								if (!dt.getStatus().name().equals(DefectStatusConstant.CLOSED.name())) {
							%>
							<div class="row">
								<div class="span12 form-group">
									<div class="text-center">
										<button class="btn btn-theme btn-medium margintop10"
											id="btnsubmit" type="submit">Update Defect</button>
									</div>
								</div>
							</div>
							<%
								}
							%>
						</form>
					</div>
				</div>
			</div>
		</section>

		<jsp:include page="common/footer.jsp" />
	</div>
	<a href="#" class="scrollup"><i
		class="icon-angle-up icon-square icon-bglight icon-2x active"></i></a>

</body>




<script>
	function defectdesc() {
		var desc = document.raisdefectform.description.value;
		var str = document.raisdefectform.str.value;
		var module = document.raisdefectform.modulename.value;

		if (desc == "") {
			alert("Please Ente Description!");
			return false;
		}

		if (str == "") {
			alert("Please Enter Steps!");
			return false;
		}

		if (module == "------- Select --------") {
			alert("Please select Model Name!");
			return false;
		}
	}

	function validateForm() {
		var desc = document.raisdefectform.description.value;
		var str = document.raisdefectform.str.value;
		var module = document.raisdefectform.modulename.value;

		if (desc == "") {
			alert("Please Ente Description!");
			return false;
		}

		if (str == "") {
			alert("Please Enter Steps!");
			return false;
		}

		if (module == "------- Select --------") {
			alert("Please select Model Name!");
			return false;
		}
	}
</script>

</html>
