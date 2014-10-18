<%@page import="integration.Authentication"%>
<%@include file="partials/header.jsp" %>
<%  Authentication.mustBeLoggedIn(request, response);%>
<%@include file="partials/footer.jsp" %>