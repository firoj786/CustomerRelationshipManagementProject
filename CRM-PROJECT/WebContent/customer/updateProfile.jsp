<!DOCTYPE html>
<%@ page import="com.src.java.crm.dto.Customer"  %>
<html lang="en">
<%@ include file="/common/links.html" %>

<%
	String uType = config.getInitParameter("uType");
%>



<% 
	Customer cust=(Customer) request.getAttribute("CustomerDetails");
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
                <li><a href="index.jsp">Home</a> <i class="icon-angle-right"></i></li>
                <li class="active">Update Profile</li>
              </ul>
              <h2>Update Your Profile</h2>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section id="content"> </section>

        <div class="row">
          <div class="span8">
           <div id="sendmessage">Your profile has been Updated. Thank you!</div>
            <div id="errormessage"></div>
            <form action="updateCustomerProfile" method="post" role="form" class="contactForm">            
    		   <div class="lrow">
      				<div class="lcol-25">
       						<label for="fname">Company Name</label>
      				</div>
    					    <div class="lcol-75">
       						<input type="text" name="cumpanyname" value="<%= cust != null ? cust.getCompany() : ""  %>" id="cumpanyname" placeholder="Company Name" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
     				 </div>
    		  </div>
              
              
              
              <div class="lrow">
      				<div class="lcol-25">
       						<label for="fname">Primary Contact</label>
      				</div>
    					    <div class="lcol-75">
       						 <input type="text" name="prcontact" value="<%= cust != null ? cust.getPrContact() : "" %>" id="prcontact" placeholder="Primary Contact" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
     				 </div>
    		  </div>

              <div class="lrow">
      				<div class="lcol-25">
       						<label for="fname">Secondary Contact</label>
      				</div>
    					    <div class="lcol-75">
       						<input type="text" name="sccontact" value="<%= cust != null ? cust.getSecContact() : "" %>" id="sccontact" placeholder="Secondary Contact" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
     				 </div>
    		  </div>    		 
    		 
    		 
    		 <div class="lrow">
      				<div class="lcol-25">
       						<label for="fname">Address</label>
      				</div>
    					    <div class="lcol-75">
       						<input type="text" name="address" value="<%= cust != null ? cust.getAddress() : ""  %>" id="address" placeholder="Address" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
     				 </div>
    		  </div> 

    		 
    		 <div class="lrow">
      				<div class="lcol-25">
       						<label for="fname">Country</label>
      				</div>
    					    <div class="lcol-75">
       						 <input type="text" name="country" value="<%= cust != null ? cust.getCountry() : ""  %>" id="country" placeholder="Country" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
     				 </div>
    		  </div> 
    		 
    		 <div class="lrow">
      				<div class="lcol-25">
       						<label for="fname">City</label>
      				</div>
    					    <div class="lcol-75">
       						<input type="text" name="city" value="<%= cust != null ? cust.getCity() : ""  %>" id="city" placeholder="City" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
     				 </div>
    		  </div>
    	
    		 
    		 
    		 <div class="lrow">
      				<div class="lcol-25">
       						<label for="fname">PinCode</label>
      				</div>
    					    <div class="lcol-75">
       						 <input type="text" name="pincode" value="<%= cust != null ? cust.getPincode() : ""  %>" id="pincode" placeholder="Pincode" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
     				 </div>
    		  </div>
                <div class="button1">                  
                    <button class="btn btn-theme btn-medium margintop10" type="submit">Save Profile</button>                  
                </div>
            
            </form>
          </div>
          <div class="span4">
            <div class="clearfix"></div>
            <aside class="right-sidebar">
              <div class="widget">
                <img src="img/customerlogin.png"  />
              </div>
            </aside>
          </div>
        </div>
      </div>
    

    <jsp:include page="/common/footer.jsp"/>
  <a href="#" class="scrollup"><i class="icon-angle-up icon-square icon-bglight icon-2x active"></i></a>

 </body>

</html>
