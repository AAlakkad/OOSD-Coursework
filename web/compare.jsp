<%@page import="integration.Authentication"%>

<%  Authentication.mustBeLoggedIn(request, response);%>

<%@include file="partials/header.jsp" %>
<%@include file="partials/footer.jsp" %>