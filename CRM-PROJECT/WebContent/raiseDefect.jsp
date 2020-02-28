<%@ page
	import="java.util.List, com.src.java.crm.dto.DesTable, com.src.java.crm.dto.Severity, com.src.java.crm.dto.User,com.src.java.crm.dto.CrmRelease,com.src.java.crm.dto.CrmModule"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="common/links.html"%>

<%
	User user = (User) session.getAttribute("UserDetails");
	DesTable dt = (DesTable) request.getAttribute("DefectDetail");
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
							<h2>Raise a defect</h2>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="content">
			<div class="container">
				<div class="row">
					<div class="span12">
						<div id="sendmessage">Your message has been sent. Thank you!</div>
						<div id="errormessage"></div>
						<form name="raisdefectform" action="raiseDefect.do" method="post" role="form"
							class="contactForm" onsubmit="return validateForm()">
							<div class="row">
								<div class="span1 form-group field">
									<label>Defect Description</label>
								</div>
								<div class="span6 form-group">
									<textarea name="description" id="description" placeholder="Defect dsecription"></textarea>
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
										<option value="<%=s.getId()%>"><%=s.getDescription()%></option>
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
									<textarea name="str" placeholder="Step to reproduce issue" id="str"></textarea>
									<div class="validation"></div>
								</div>
								<div class="span1 form-group field">
									<label>Assigned to </label>
								</div>
								<div class="span4 form-group field">
									<select name="assignedTo">
										<%
											List<User> userList = null;
																											if(user.getRoleId() == 2) {
																												userList = (List<User>) application.getAttribute("EmployeeAndSalesList");
																											} else if(user.getRoleId() == 4) {
																												userList = (List<User>) application.getAttribute("EmployeeList");
																											}
																											for (User u : userList) {
										%>
										<option value="<%=u.getUserId()%>"><%=u.getFname()%>&nbsp<%=u.getLname()%></option>
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
									<select name="releaseversion">
										<%
											List<CrmRelease> simReleaseList = (List<CrmRelease>) application.getAttribute("GpsSimReleaseList");

																											for (CrmRelease g : simReleaseList) {
										%>
										<option value="<%=g.getId()%>"><%=g.getVersion()%></option>
										<%
											}
										%>
									</select>
								</div>

								<div class="span1 form-group field">
									<label>Module Name </label>
								</div>
								<div class="span3 form-group field">
									<select name="modulename" id = "modulename">
										<option >------- Select --------</option>
										<%
											List<CrmModule> moduleList = (List<CrmModule>) application.getAttribute("GpsModuleList");

																			for (CrmModule m : moduleList) {
										%>
										<option value="<%=m.getId()%>"><%=m.getDescription()%></option>
										<%
											}
										%>
									</select>
								</div>

								<div class="span1 form-group field">
									<label>Module Version </label>
								</div>
								<div class="span3 form-group field">
									<select name="moduleversion" id="moduleversion">
										<option>------- Select --------</option>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="span1 form-group field">
									<label>Fixed In </label>
								</div>
								<div class="span3 form-group field">
									<input type="text" name="fixedIn" value="" readonly />
								</div>
								<div class="span1 form-group field">
									<label>Resolved on </label>
								</div>
								<div class="span3 form-group field">
									<input type="date" name="resolutionDate" value="" readonly />
								</div>
								<div class="span1 form-group field">
									<label>Resolution </label>
								</div>
								<div class="span3 form-group field">
									<textarea name="resolution" readonly></textarea>
								</div>
							</div>
							<div class="row">
								<div class="span12 form-group">
									<div class="text-center">
										<button class="btn btn-theme btn-medium margintop10" id="btnsubmit" type="submit">Create Defect</button>
									</div>
								</div>
							</div>
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
	    $(document).ready(function() {

			$('#modulename').change(function(event) {
				var $moduleName = $("#modulename").val();
				$.get('raiseDefect.do', {
					moduleId : $moduleName
				}, function(responseJson) {
					var $select = $('#moduleversion');
					$select.find('option').remove();
					var items = responseJson;					
					for (index = 0; index < items.length; ++index) {
						$('<option>').val(items[index]).text(items[index]).appendTo($select);   
					}
				});
			});
	});

	
	function defectdesc() {
		var desc= document.raisdefectform.description.value;
		var str = document.raisdefectform.str.value;
		var module = document.raisdefectform.modulename.value;

        if (desc == "") {
            alert("Please Ente Description!");
            return false;
        }
        
        if (str == "") {
        	alert ("Please Enter Steps!");
        	return false;
        }
        
        if (module == "------- Select --------") {
            alert("Please select Model Name!");
            return false;
        }
	}
	
	function validateForm() {
		var desc= document.raisdefectform.description.value;
		var str = document.raisdefectform.str.value;
		var module = document.raisdefectform.modulename.value;
		
        if (desc == "") {
            alert("Please Ente Description!");
            return false;
        }
        
        if (str == "") {
        	alert ("Please Enter Steps!");
        	return false;
        }
        
        if (module == "------- Select --------") {
            alert("Please select Model Name!");
            return false;
        }
	}
</script>

</html>
