<%@page import="com.src.java.crm.dto.User"%>
<%@ page import="java.util.List"%>
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
							<h2>Total Enrolled Employee</h2>
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
							<table class="table table-striped">
								<thead>
									<tr>
										<td>Login ID</td>
										<td>First NAME</td>
										<td>Active/D-Active
										<td>Action</td>
									</tr>
								</thead>
								<tbody>
									<%
										List<User> enrList = (List<User>) request.getAttribute("EnrolledEmployeeList");
										for (User u : enrList) {
									%>
									<tr>
										<td><%=u.getUserId()%></td>
										<td><%=u.getFname()%></td>
										<td><%=u.isActive() %></td>
										
										<td>	<%if (u.isActive() == true) {
										%> <a href="enrolemp.do?id=u.getUserId()%>">DeActive</a> <%
										} else {
											%> <a href="enrolemp.do?id=u.getUserId()%>">Active</a> <%
											}
										%>

										</td>
										<%-- <td><a href="enrolemp.do?id=<%=u.getUserId()%>">Active/Dea</a></td> --%>
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
