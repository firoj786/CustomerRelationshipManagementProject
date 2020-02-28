<!DOCTYPE html>
<%@ page import="com.src.java.crm.dto.Customer, com.src.java.crm.dto.User"%>
<html lang="en">
<%@ include file="/common/links.html"%>

<%
	String uType = config.getInitParameter("uType");
%>



<%
	User user = (User) request.getAttribute("UserDetail");
	Customer cust = (Customer) request.getAttribute("CustomerDetail");
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
								<li><a href="index.jsp">Home</a> <i
									class="icon-angle-right"></i></li>
								<%
									if (uType.equals("3")) {
								%>
								<li class="active">Register New Employee</li>
								<%
									} else if (uType.equals("4")) {
								%>
								<li class="active">Register New Sales</li>
								<%
									}
								%>
							</ul>
							
							<%
								if (uType.equals("3")) {
							%>
							<h2>Register New Employee</h2>
							<%
								} else if (uType.equals("4")) {
							%>
							<h2>Register New Sales</h2>
							<%
								}
							%>
							<h2>
							<font color="red"><span id="eMessage"></span><%=(request.getAttribute("eMsg") != null ? request.getAttribute("eMsg") : "")%></font>
							</h2>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="content"></section>

		<div class="row">
			<div class="span8">
				<div id="sendmessage">Your profile has been Updated. Thank you!</div>
				<div id="errormessage"></div>
				
				<%
					if (uType.equals("3")) {
				%>
					<form name="registerEmployeeForm" action="registerEmployeeProfile" method="post" role="form"
					class="contactForm" onsubmit="return validateForm()">
				<%
					} else if (uType.equals("4")) {
				%>
					<form name="registerSalesForm" action="registerSalesProfile" method="post" role="form"
					class="contactForm" onsubmit="return validateForm()">
				<%
					}
				%>
					 <div class="lrow">
      				       <div class="lcol-25">
       						  <label for="fname">Login Id</label>
      				       </div>
    					      <div class="lcol-75">
       						 <input type="text" name="loginId"
								value="<%=user != null ? user.getLoginId() : ""%>"
								id="loginId" placeholder="Enter Login Id" required/>
     				       </div>
    		         </div>
    		          <div class="lrow">
      				       <div class="lcol-25">
       						  <label for="fname">Email</label>
      				       </div>
    					      <div class="lcol-75">
       						<input type="email" name="emailId"
								value="<%=user != null ? user.getEmailId() : ""%>"
								id="emailId" placeholder="Enter Email Id" required/>
     				       </div>
    		         </div>
    		          <div class="lrow">
      				       <div class="lcol-25">
       						  <label for="fname">Password</label>
      				       </div>
    					      <div class="lcol-75">
       						<input type="password" name="password"
								value="<%=user != null ? user.getPassword() : ""%>"
								id="password" placeholder="Enter Password" required/>
     				       </div>
    		         </div>
    		         <div class="lrow">
      				       <div class="lcol-25">
       						  <label for="fname">Re-Enter Password</label>
      				       </div>
    					      <div class="lcol-75">
       						<input type="password" name="cpassword"
								id="cpassword" placeholder="Re-Enter Password" 
								required onblur="validatePassword()"/><span id="cPassIdField"></span>
     				       </div>
    		         </div>
    		         
    		          <div class="lrow">
      				       <div class="lcol-25">
       						  <label for="fname">First Name</label>
      				       </div>
    					      <div class="lcol-75">
       						<input type="text" name="fname"
								value="<%=user != null ? user.getFname() : ""%>"
								id="fname" placeholder="Enter first name" required/>
     				       </div>
    		         </div>
    		         <div class="lrow">
      				       <div class="lcol-25">
       						  <label for="fname">Last Name</label>
      				       </div>
    					      <div class="lcol-75">
       						<input type="text" name="lname"
								value="<%=user != null ? user.getLname() : ""%>"
								id="lname" placeholder="Enter last name" required/>
     				       </div>
    		         </div>
						<div class="button1">
							<button class="btn btn-theme btn-medium margintop10"
								type="submit">Save Profile</button>
						</div>
				
				</form>
			</div>
			<div class="span4">
				<div class="clearfix"></div>
				<aside class="right-sidebar">

					<div class="widget">
						<img src="img/register.jpg" />
					</div>
				</aside>
			</div>
		</div>
	</div>


	<jsp:include page="/common/footer.jsp" />
	<a href="#" class="scrollup"><i
		class="icon-angle-up icon-square icon-bglight icon-2x active"></i></a>

</body>

<script>
	function validatePassword() {
		var pass = document.registerCustomerForm.password.value;
		var cpass = document.registerCustomerForm.cpassword.value;
		
		if(pass != cpass) {
			document.getElementById('cPassIdField').innerText="Password / Confirm password mismatched.";  
		} else {
			document.getElementById('cPassIdField').innerText="";  
		}
	}
	
	function validateForm() {
		var pass = document.registerCustomerForm.password.value;
		var cpass = document.registerCustomerForm.cpassword.value;
		
		if(pass != cpass) {
			document.getElementById('cPassIdField').innerText="Password / Confirm password mismatched.";  
			return false;
		} else {
			document.getElementById('cPassIdField').innerText="";
			return true;
		}
	}
</script>

</html>
