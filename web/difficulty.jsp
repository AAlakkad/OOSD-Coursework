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

        <% DAO dao = DAO.getQuizDAO();
            HashMap<Integer,String> difficulties = dao.getDifficultiesNames();
            Iterator i = difficulties.keySet().iterator();
            while (i.hasNext()) {
                Integer difficultyId = Integer.parseInt(i.next().toString());
        %>
        <div class="radio">
            <label for="topic_<%= difficultyId%>">
                <input type="radio" value="<%=difficultyId%>" name="difficulty_id" id="topic_<%= difficultyId%>"/>
                <%= difficulties.get(difficultyId).toString()%>
            </label>
        </div>
        <%
            }
        %>
        <input type="submit" value="Start!" class="btn btn-success">
    </form>
</div>
<%@include file="partials/footer.jsp" %>