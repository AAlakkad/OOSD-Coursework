<%@page import="integration.Authentication"%>
<% Authentication.mustBeLoggedIn(request, response);%>
<%@include file="partials/header.jsp" %>
<h1>Welcome!</h1>
<br>
<p>Please select action from the top main menu.</p>
<%@include file="partials/footer.jsp" %>