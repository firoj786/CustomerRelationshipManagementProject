<!DOCTYPE html>
<%@ page
	import="com.src.java.crm.dto.Customer, com.src.java.crm.dto.User"%>
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
								<li class="active">Change Password</li>
							</ul>

							<h2>Change Password</h2>
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
				<div id="sendmessage">Your profile has been Updated. Thank
					you!</div>
				<div id="errormessage"></div>

				<form name="changePasswordForm" action="changePassword" method="post" role="form"
					class="contactForm" onsubmit="return validateForm()">
					<div class="lrow">
						<div class="lcol-25">
							<label for="fname">Old Password</label>
						</div>
						<div class="lcol-75">
							<input type="password" name="oldPassword" id="oldPassword"
								placeholder="Enter Old Password" required />
						</div>
					</div>
					<div class="lrow">
						<div class="lcol-25">
							<label for="fname">New Password</label>
						</div>
						<div class="lcol-75">
							<input type="password" name="newPassword" id="newPassword"
								placeholder="Enter New Password" required />
						</div>
					</div>
					<div class="lrow">
						<div class="lcol-25">
							<label for="fname">Re-Enter New Password</label>
						</div>
						<div class="lcol-75">
							<input type="password" name="cpassword" id="cpassword"
								placeholder="Re-Enter New Password" required
								onblur="validatePassword()" /><span id="cPassIdField"></span>
						</div>
					</div>

					<div class="button1">
						<button class="btn btn-theme btn-medium margintop10" type="submit">Change
							Password</button>
					</div>

				</form>
			</div>
			<div class="span5">
				<div class="clearfix"></div>
				<aside class="right-sidebar">

					<div class="widget">
						<img src="img/addnew.jpg" />
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
		var pass = document.changePasswordForm.newPassword.value;
		var cpass = document.changePasswordForm.cpassword.value;

		if (pass != cpass) {
			document.getElementById('cPassIdField').innerText = "New Password / Confirm password mismatched.";
		} else {
			document.getElementById('cPassIdField').innerText = "";
		}
	}

	function validateForm() {
		var pass = document.changePasswordForm.newPassword.value;
		var cpass = document.changePasswordForm.cpassword.value;

		if (pass != cpass) {
			document.getElementById('cPassIdField').innerText = "New Password / Confirm password mismatched.";
			return false;
		} else {
			document.getElementById('cPassIdField').innerText = "";
			return true;
		}
	}
</script>

</html>
