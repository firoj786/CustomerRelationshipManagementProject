<%@ page
	import="java.util.List, com.src.java.crm.dto.Severity, com.src.java.crm.utilities.DefectStatusConstant"%>
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
									<li class="active">Quick Search</li>
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
						<form action="searchDefectList" method="post" role="form" class="contactForm">
							<div class="lrow">
								<div class="lcol-25">
									<label for="fname">Keyword</label>
								</div>
								<div class="lcol-75">
									<input type="text" name="keyword" id="keyword"
										placeholder="Enter Keywords (separated using comma)" />
								</div>
							</div>
							<div class="lrow">
								<div class="lcol-25">
									<label>Severity </label>
								</div>
								<div class="lcol-75">
									<select name="severity">
										<option value="-1">Select Severity</option>
										<%
											List<Severity> severityList = (List<Severity>) application.getAttribute("SeverityList");
											for (Severity s : severityList) {
										%>
										<option value="<%=s.getId()%>"><%=s.getDescription()%></option>
										<%
											}
										%>
									</select>
								</div>

								<div class="span form-group">
									<div class="text-center">
										<button class="btn btn-theme btn-medium margintop10"
											type="submit">Search</button>
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
