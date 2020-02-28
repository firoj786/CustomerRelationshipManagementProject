<%@ page import="java.util.List, com.src.java.crm.dto.Severity"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="common/links.html"%>

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
								<li><a href="#">Pages</a> <i class="icon-angle-right"></i></li>
								<li class="active">Typography</li>
							</ul>
							<h2>Add Severity</h2>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="content">
			<div class="container">
				<div class="row">
					<h2>
						<font color="red"><%=(request.getAttribute("eMsg") != null ? request.getAttribute("eMsg") : "")%></font>
						<font color="green"><%=(request.getAttribute("sMsg") != null ? request.getAttribute("sMsg") : "")%></font>
					</h2>
					<div class="row">
						<div class="span5">
							<form action="severity.do" method="post" role="form"
								class="contactForm">

								<div class="lrow">
									<div class="lcol-25">
										<label>Add Severity:</label>
									</div>
									<div class="lcol-75">
										<input name="sevDescription" type="text" required/>
									</div>
								</div>
								<div class="row">
									<div class="button">
										<button class="btn btn-theme btn-medium margintop10"
											type="submit">Submit</button>
									</div>
								</div>
							</form>

							<table class="table table-striped">
								<thead>
									<tr>
										<td>ID</td>
										<td>NAME</td>
										<td>Action</td>
									</tr>
								</thead>

								<tbody>
									<%
										List<Severity> severityList = (List<Severity>) request.getAttribute("SeverityList");
										for (Severity s : severityList) {
									%>
									<tr>
										<td><%=s.getId()%></td>
										<td><%=s.getDescription()%></td>
										<td><a href="severity.do?id=<%= s.getId()%>">DELETE</a></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>

					</div>

				</div>
			</div>
		</section>
</div>
		<jsp:include page="common/footer.jsp" />
		<a href="#" class="scrollup"><i
			class="icon-angle-up icon-square icon-bglight icon-2x active"></i></a>
</body>

</html>
