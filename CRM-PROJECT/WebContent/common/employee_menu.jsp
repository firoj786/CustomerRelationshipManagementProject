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
		<li class="dropdown"><a href="#"><%=resource.getString("customer.menu.defects") %><i class="icon-angle-down"></i></a>
			<ul class="dropdown-menu">
				<li><a href="viewDefectList.do?type=all"><%=resource.getString("customer.menu.viewall") %></a></li>
				<li><a href="viewDefectList.do?type=open"><%=resource.getString("customer.menu.allopen") %></a></li>
				<li><a href="viewDefectList.do?type=closed"><%=resource.getString("customer.menu.allclosed") %></a></li>
				<li><a href="viewDefectList.do?type=priority"><%=resource.getString("customer.menu.topcustomer") %></a></li>
			</ul></li>
		<li ><a href="searchDefect.do"><%=resource.getString("customer.menu.qsearch") %></a></li>
		<li><a href="contact.jsp"><%=resource.getString("home.contact") %></a></li>
		<li class="dropdown"><a href="#"><%=user.getFname()%> <i
				class="icon-angle-down"></i></a>
			<ul class="dropdown-menu">
				<li><a href="changePassword.do"><%=resource.getString("login.change.password") %></a></li>
				<li><a href="logout"><%=resource.getString("crm.logout")%></a></li>
			</ul></li>
	</ul>
</nav>
