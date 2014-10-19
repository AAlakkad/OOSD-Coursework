<%@ taglib prefix="quiz" uri="WEB-INF/radios.tld"%>

<%@page import="java.util.HashMap"%>
<%@page import="integration.Authentication"%>
<%@page import="business.TransferObjects.Topic" %>
<%@page import="integration.DAO" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>

<%  Authentication.mustBeLoggedIn(request, response);%>

<%@include file="partials/header.jsp" %>
<div class="col-md-8 col-md-offset-2">
    <h1>Choose topic to start:</h1>

    <form method="post" action="/Relay">
        <input type="hidden" name="action" value="Quiz">
        <input type="hidden" name="sub_action" value="choose_topic">
        
        <quiz:itemsRadio tabletype="topics"/>
        
        <input type="submit" value="Start!" class="btn btn-success">
    </form>
</div>
<%@include file="partials/footer.jsp" %>