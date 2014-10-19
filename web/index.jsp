<%@page import="integration.Authentication"%>
<% Authentication.mustBeLoggedIn(request, response);
%>
<%@include file="partials/header.jsp" %>
<div class="col-md-8 col-md-offset-2">
    <%    String error = (String) session.getAttribute("error");
        if (error != null) {
    %>
    <div class="alert alert-danger" role="alert">
        <%= error%>
    </div>
    <%
            session.removeAttribute("error");
        }
    %>
    <h1>Welcome!</h1>
    <br>
    <p>Please select action from the top main menu.</p>
</div>
<%@include file="partials/footer.jsp" %>