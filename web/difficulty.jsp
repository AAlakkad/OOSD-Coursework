<%@ taglib prefix="quiz" uri="WEB-INF/radios.tld"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="integration.DAO"%>
<%@page import="integration.Authentication"%>
<%@page import="business.TransferObjects.Difficulty"%>

<%  Authentication.mustBeLoggedIn(request, response);%>

<%@include file="partials/header.jsp" %>
<div class="col-md-8 col-md-offset-2">
    <h1>Choose topic to start:</h1>

    <form method="post" action="/Relay">
        <input type="hidden" name="action" value="Quiz">
        <input type="hidden" name="sub_action" value="choose_difficulty">

        <quiz:itemsRadio tabletype="difficulties"/>
        
        <input type="submit" value="Start!" class="btn btn-success">
    </form>
</div>
<%@include file="partials/footer.jsp" %>