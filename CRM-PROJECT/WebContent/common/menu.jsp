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
		<li class="dropdown"><a href="#"><%=resource.getString("features.menu")%>
				<i class="icon-angle-down"></i></a>
			<ul class="dropdown-menu">
				<li><a href=""><%=resource.getString("Features.menu.wsmon")%></a></li>
				<li><a href=""><%=resource.getString("Features.menu.dbmon")%></a></li>
			</ul></li>

		<li class="dropdown"><a href="#"><%=resource.getString("login.menu")%>
				<i class="icon-angle-down"></i></a>
			<ul class="dropdown-menu">
				<li><a href="customerLogin"><%=resource.getString("login.menu.customer")%></a></li>
				<li><a href="salesLogin"><%=resource.getString("login.menu.sales")%></a></li>
				<li><a href="employeeLogin"><%=resource.getString("login.menu.crm.employee")%></a></li>
			</ul></li>

		<li><a href="contact.jsp"><%=resource.getString("home.contact") %> </a></li>

	</ul>
</nav>
