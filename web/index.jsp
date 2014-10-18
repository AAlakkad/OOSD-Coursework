<%@page import="integration.Authentication"%>
<%@include file="partials/header.jsp" %>
<%
if(! Authentication.isLoggedIn(request)) {
    Authentication.redirectLogIn(request, response);
}
%>
<h1>Welcome!</h1>
<br>
<p>Please select action from the top main menu.</p>
<%@include file="partials/footer.jsp" %>