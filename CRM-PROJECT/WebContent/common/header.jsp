<%@ page
	import="com.src.java.crm.dto.User, java.util.*, com.src.java.locales.ExtendedControl"%>
<%
	User user = (User) session.getAttribute("UserDetails");
	ResourceBundle resource = ResourceBundle.getBundle("com.src.crm.resources.messages", request.getLocale(),
			new ExtendedControl("UTF8"));
%>
<!-- start header -->
<header>
	<div class="top">
		<div class="container">
			<div class="row">
				<div class="span6">
					<p class="topcontact">
						<i class="icon-phone"></i> +91 7351109625
					</p>
				</div>
				<div class="span6">

					<ul class="social-network">
						<li><a href="https://www.facebook.com/fkhanjava/" data-placement="bottom" title="Facebook"><i
								class="icon-facebook icon-white"></i></a></li>
						<li><a href="https://twitter.com/fkkhan_java" data-placement="bottom" title="Twitter"><i
								class="icon-twitter icon-white"></i></a></li>
						<li><a href="https://www.linkedin.com/in/fkhan-java" data-placement="bottom" title="Linkedin"><i
								class="icon-linkedin icon-white"></i></a></li>
						<li><a href="#" data-placement="bottom" title="Pinterest"><i
								class="icon-pinterest  icon-white"></i></a></li>
						<li><a href="#" data-placement="bottom" title="Google +"><i
								class="icon-google-plus icon-white"></i></a></li>
						<li><a href="#" data-placement="bottom" title="Dribbble"><i
								class="icon-dribbble icon-white"></i></a></li>
					</ul>

				</div>
			</div>
		</div>
	</div>
	<div class="container">


		<div class="row nomargin">
			<div class="span4">
				<div class="logo">
					<a href="index.jsp"><img src="img/logo.png" width="120"
						height="50" /></a>
				</div>
			</div>
			<div class="span8">
				<div class="navbar navbar-static-top">
					<div class="navigation">
						<%
							if (user != null && user.getRoleId() == 1) {
						%>
						<jsp:include page="admin_menu.jsp" />
						<%
							} else if (user != null && user.getRoleId() == 2) {
						%>
						<jsp:include page="customer_menu.jsp" />
						<%
							} else if (user != null && user.getRoleId() == 3) {
						%>
						<jsp:include page="employee_menu.jsp" />
						<%
							} else if (user != null && user.getRoleId() == 4) {
						%>
						<jsp:include page="sales_menu.jsp" />
						<%
							} else {
						%>
						<jsp:include page="menu.jsp" />
						<%
							}
						%>
					</div>
					<!-- end navigation -->
				</div>
			</div>
		</div>
	</div>
</header>
<!-- end header -->