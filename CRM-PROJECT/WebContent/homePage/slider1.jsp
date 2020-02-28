<%@ page
	import="com.src.java.crm.dto.User, java.util.*, com.src.java.locales.ExtendedControl"%>
<%
	User user = (User) session.getAttribute("UserDetails");
	ResourceBundle resource = ResourceBundle.getBundle("com.src.crm.resources.messages", request.getLocale(),
			new ExtendedControl("UTF8"));
%>

        <!-- slide 1 here -->
        <div data-src="img/slides/camera/slide1/crm.jpg">
          <div class="camera_caption ">
            <div class="container">
              <div class="row">
                <div class="span6">
                  <h2 class=""><strong><%=resource.getString("all.system.grow") %></strong></h2>
                  <p class=""> Software to fuel your growth and build deeper relationships, from first hello to happy customer and beyond.</p>
                </div>
              </div>
            </div>
          </div>
        </div>