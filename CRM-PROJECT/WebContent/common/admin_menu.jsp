<%@ page
	import="com.src.java.crm.dto.User, java.util.*, com.src.java.locales.ExtendedControl"%>
<%
	User user = (User) session.getAttribute("UserDetails");
	ResourceBundle resource = ResourceBundle.getBundle("com.src.crm.resources.messages", request.getLocale(),
			new ExtendedControl("UTF8"));
%>
<nav>
	<ul class="nav topnav">
		<li class="active"><a href="<%=request.getContextPath()%>"><i
				class="icon-home"></i> <%=resource.getString("home.menu")%></a></li>
		<li class="dropdown"><a href="#"><%=resource.getString("admin.add")%>
				<i class="icon-angle-down"></i></a>
			<ul class="dropdown-menu">
				<li><a href="severity.do"><%=resource.getString("admin.severity")%></a></li>
				<li><a href="GpsModule.do"><%=resource.getString("admin.module")%></a></li>
				<li><a href="enrolemp.do"><%=resource.getString("admin.enrolledUser")%></a></li>
				<li><a href=""><%=resource.getString("admin.questionnaire")%></a></li>
				
			</ul></li>
		<li class="dropdown"><a href="#"><%=resource.getString("admin.enroll")%><i
				class="icon-angle-down"></i></a>
			<ul class="dropdown-menu">
				<li><a href="empregister"><%=resource.getString("admin.enroll.employee")%></a></li>
				<li><a href="salregister"><%=resource.getString("admin.enroll.salse")%></a></li>
			</ul></li>

		<li class="dropdown"><a href="#"><%=user.getFname()%> <i
				class="icon-angle-down"></i></a>
			<ul class="dropdown-menu">
				<li><a href="changePassword.do"><%=resource.getString("login.change.password")%></a></li>
				<li><a href="logout"><%=resource.getString("crm.logout")%></a></li>
			</ul></li>

	</ul>
</nav>