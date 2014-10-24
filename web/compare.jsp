<%@ taglib prefix="quiz" uri="WEB-INF/radios.tld"%>

<%@page import="presentation.servlets.Quiz"%>
<%@page import="java.util.Iterator"%>
<%@page import="business.TransferObjects.Topic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="integration.DAO"%>
<%@page import="integration.Authentication"%>

<%  Authentication.mustBeLoggedIn(request, response);%>

<%
String message = null;
if(request.getParameter("sub_action") != null) {
    message = Quiz.compareResult(request, response);
}
%>

<%@include file="partials/header.jsp" %>
<div class="col-md-8 col-md-offset-2">
    <h1>Choose topic to compare</h1>

    <form method="post" action="Relay">
        <input type="hidden" name="action" value="compare.jsp">
        <input type="hidden" name="sub_action" value="compare">

        <%
            if(message != null) {
                %>
                <div class="alert alert-info">
                    <%= message%>
                </div>
                <%
            }
        %>

        <quiz:itemsRadio tabletype="topics"/>

        <input type="submit" value="Compare!" class="btn btn-success">
    </form>
</div>
<%@include file="partials/footer.jsp" %>