<%@ page
	import="java.util.List, com.src.java.crm.dto.DesTable, com.src.java.crm.dto.Severity, 
	com.src.java.crm.dto.User,com.src.java.crm.dto.CrmRelease,com.src.java.crm.dto.CrmModule,static com.src.java.crm.utilities.StringUtility.*,static com.src.java.crm.utilities.CrmUtility.*"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="common/links.html"%>

<%
	User user = (User) session.getAttribute("UserDetails");
	List<DesTable> defectList = (List<DesTable>) request.getAttribute("DefectList");
	String type = (String) request.getAttribute("type");
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
								<li class="active">All <%=type%> Defect
								</li>
							</ul>
							<h2>
								List of all
								<%=type%>
								Defect
							</h2>
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
						<table class="table table-striped">
							<thead>
								<tr>
									<td>Description</td>
									<td>Raised By <%
										if(type != null && ! type.isEmpty()) {
									%> <a href="viewDefectList.do?order=customer"><i class="icon-caret-down icon-large"></i></a>&nbsp; <a
										href="viewDefectList.do?order=customer&by=desc"><i class="icon-caret-up icon-large"></i></a><%
 	}
 %></td>
									<td>Severity</td>
									<td>Creation Date</td>
									<td>Status</td>
									<td>Module Name</td>
								</tr>
							</thead>

							<tbody>
								<%
									for (DesTable dt : defectList) {
								%>
								<tr>
									<td><a href="defectDetail?defectId=<%=dt.getId()%>"><%=getSubString(dt.getDesc(), 20)%></a></td>
									<td><%=dt.getUserFullName()%></td>
									<td><%=dt.getSevDescription()%></td>
									<td><%=dt.getCreationDate()%></td>
									<td><%=dt.getStatus()%></td>
									<td><%=getModuleName(dt.getModuleNameId(), (List<CrmModule>) application.getAttribute("GpsModuleList"))%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
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
	    $(document).ready(
			function() {

				$('#modulename').change(
						function(event) {
							var $moduleName = $("#modulename").val();
							$.get('raiseDefect.do', {
								moduleId : $moduleName
							}, function(responseJson) {
								var $select = $('#moduleversion');
								$select.find('option').remove();
								var items = responseJson;
								for (index = 0; index < items.length; ++index) {
									$('<option>').val(items[index]).text(
											items[index]).appendTo($select);
								}
							});
						});
			});

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
