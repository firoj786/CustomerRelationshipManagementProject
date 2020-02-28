<%@ page
	import="com.src.java.crm.dto.User, java.util.*, com.src.java.locales.ExtendedControl"%>

<!DOCTYPE html>
<html lang="en">
<%
	User user = (User) session.getAttribute("UserDetails");
	ResourceBundle resource = ResourceBundle.getBundle("com.src.crm.resources.messages", request.getLocale(),
			new ExtendedControl("UTF8"));
	response.setCharacterEncoding("UTF-8");
%>
<%@ include file="common/links.html"%>

<%
	String uType = config.getInitParameter("uType");
%>

<body>

	<div id="wrapper">

		<jsp:include page="/common/header.jsp" />

		<section id="inner-headline">
			<div class="container">
				<div class="row">
					<div class="span12">
						<div class="inner-heading">
							<ul class="breadcrumb">
								<li><a href="index.jsp"><%=resource.getString("home.menu")%></a> <i
									class="icon-angle-right"></i></li>
								<%
									if (uType.equals("1")) {
								%>
								<li class="active">Admin Login</li>
								<%
									} else if (uType.equals("2")) {
								%>
								<li class="active">Customer Login</li>
								<%
									} else if (uType.equals("3")) {
								%>
								<li class="active">Employee Login</li>
								<%
									} else if (uType.equals("4")) {
								%>
								<li class="active">Sales Login</li>
								<%
									}
								%>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="content">
			<div class="container">
				<div class="row">
					<div class="span8">
						<div id="errormessage"></div>
						<h2>
							<font color="red"><%=(request.getAttribute("eMsg") != null ? request.getAttribute("eMsg") : "")%></font>
							<font color="green"><%=(request.getAttribute("sMsg") != null ? request.getAttribute("sMsg") : "")%></font>
						</h2>
						<form action="validateLogin" method="post" role="form"
							class="contactForm">
							<div class="row">
								<div class="span4 form-group field">
									<input type="text" name="username" id="username"
										placeholder="Enter Username" data-rule="minlen:4"
										data-msg="Please enter at least 4 chars" /> <input
										type="hidden" name="roleId" value="<%=uType%>" />
									<div class="validation"></div>
								</div>

								<div class="span4 form-group">
									<input type="password" name="password" id="password"
										placeholder="Enter Password" data-rule="email"
										data-msg="Please enter a valid password"
										value="<%=(request.getAttribute("loginId") != null ? request.getAttribute("loginId") : "")%>" />
									<div class="validation"></div>
								</div>
								<div class="span8 form-group">
									<div class="text-center">
										<button class="btn btn-theme btn-medium margintop10"
											type="submit">Sign In</button>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="span4">
						<div class="clearfix"></div>
						<aside class="right-sidebar">

							<div class="widget">
								<img src="img/loginimage.jpg" />

							</div>
						</aside>
					</div>
				</div>
			</div>
		</section>

		<jsp:include page="common/footer.jsp" />
	</div>
	<a href="#" class="scrollup"><i
		class="icon-angle-up icon-square icon-bglight icon-2x active"></i></a>

</body>

</html>
