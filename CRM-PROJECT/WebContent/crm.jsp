<%
	response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="common/links.html"%>

<body>

	<div id="wrapper">

		<jsp:include page="/common/header.jsp" />

		<!-- section featured -->
		<section id="featured">

			<!-- slideshow start here -->
			
			<font color="red"><%=(request.getAttribute("eMsg") != null ? request.getAttribute("eMsg") : "")%></font>
			<font color="green"><%=(request.getAttribute("msg") != null ? request.getAttribute("msg") : "")%></font>
			<div class="camera_wrap" id="camera-slide">

				<jsp:include page="homePage/slider1.jsp" />

				<jsp:include page="homePage/slider2.jsp" />

				<jsp:include page="homePage/slider3.jsp" />

			</div>

		</section>
		<!-- /section featured -->

		<section id="content">
			<div class="container">


	         		<div class="row">
					<div class="span12">
						<div class="line"></div>
					</div>
				</div> 

				<div class="row">
				<!-- 	<div class="span12">
						<div class="row">
							<div class="span12">
								<div class="aligncenter">
									<h3>
										Our <strong>Policies </strong>
									</h3>
									<p>Lorem ipsum dolor sit amet, labores dolorum scriptorem
										eum an, te quodsi sanctus neglegentur.</p>
								</div>
							</div>
						</div> -->
						
										<div class="row">
					<div class="span12">
						<div class="row">
							<div class="span4">
								<div class="box flyLeft">
									<div class="icon">
										<i class="ico icon-circled icon-bgdark icon-cog active icon-3x"></i>
									</div>
									<div class="text">
										<h4>
											Website CRM integration <strong> </strong>
										</h4>
										<p>Customer relationship management (CRM) integration is our teams specialist area. 
										The methodology we use is based on establishing a clear data contract with either the vendor or owner of
										 the back office platform so that we set clear lines of responsibility and support. </p>
									</div>
								</div>
							</div>

							<div class="span4">
								<div class="box flyIn">
									<div class="icon">
										<i class="ico icon-circled icon-bgdark icon-laptop active icon-3x"></i>
									</div>
									<div class="text">
										<h4>
											Integrate your CRM With Your Web site <strong></strong>
										</h4>
										<p>A CRM is a tool used by businesses to manage their interactions with their current and potential customers. 
										It makes for happy, well-informed customers which, in turn, makes for healthy profit margins.</p>
										
									</div>
								</div>
							</div>
							<div class="span4">
								<div class="box flyRight">
									<div class="icon">
										<i
											class="ico icon-circled icon-bgdark icon-briefcase active icon-3x"></i>
									</div>
									<div class="text">
										<h4>
											Benefits of CRM integration <strong></strong>
										</h4>
										<p>CRM integration for your website is good for your business. It means less time spent manually entering customer details into your CRM from multiple inlets and more leads generated than ever before.</p>
										
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
						


				<!-- 			<div class="row">

							<div class="span3">
								<div class="pricing-box-wrap animated-fast flyIn">
									<div class="pricing-heading">
										<h3>
											Raise Defect<strong></strong>
										</h3>
									</div>
									<div class="pricing-content">
										<ul>
											<li><i class="icon-ok"></i> 24x7 support available</li>
											<li><i class="icon-ok"></i> No hidden fees</li>
											<li><i class="icon-ok"></i> Stop anytime easily</li>
										</ul>
									</div>
									<div class="pricing-action">
										<a href="#" class="btn btn-medium btn-theme"><i
											class="icon-chevron-down"></i> LogIN</a>
									</div>
								</div>
							</div>

						<div class="span3">
								<div class="pricing-box-wrap animated-fast flyIn">
									<div class="pricing-heading">
										<h3>
											Raise Queries<strong></strong>
										</h3>
									</div>
									<div class="pricing-content">
										<ul>
											<li><i class="icon-ok"></i> 24x7 support available</li>
											<li><i class="icon-ok"></i> No hidden fees</li>
											<li><i class="icon-ok"></i> Stop anytime easily</li>
										</ul>
									</div>
									<div class="pricing-action">
										<a href="#" class="btn btn-medium btn-theme"><i
											class="icon-chevron-down"></i> LogIN</a>
									</div>
								</div>
							</div>

							<div class="span3">
								<div class="pricing-box-wrap special animated-slow flyIn">
									<div class="pricing-heading">
										<h3>
											Any Suggestion <strong></strong>
										</h3>
									</div>
									<div class="pricing-content">
										<ul>
											<li><i class="icon-ok"></i> 24x7 support available</li>
											<li><i class="icon-ok"></i> No hidden fees</li>
											<li><i class="icon-ok"></i> Stop anytime easily</li>
										</ul>
									</div>
									<div class="pricing-action">
										<a href="#" class="btn btn-medium btn-theme"><i
											class="icon-chevron-down"></i> LogIN</a>
									</div>
								</div>
							</div>

							<div class="span3">
								<div class="pricing-box-wrap animated flyIn">
									<div class="pricing-heading">
										<h3>
											Enhancements <strong></strong>
										</h3>
									</div>
									<div class="pricing-content">
										<ul>
											<li><i class="icon-ok"></i> 24x7 support available</li>
											<li><i class="icon-ok"></i> No hidden fees</li>
											<li><i class="icon-ok"></i> Stop anytime easily</li>
										</ul>
									</div>
									<div class="pricing-action">
										<a href="#" class="btn btn-medium btn-theme"><i
											class="icon-chevron-down"></i> LogIN</a>
									</div>
								</div>
							</div>
						</div> -->
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
