<%@page import="integration.Authentication"%>
<%@page import="business.TransferObjects.Topic" %>
<%@page import="integration.DAO" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@include file="partials/header.jsp" %>
<%    if (! Authentication.isLoggedIn(request)) {
        Authentication.redirectLogIn(request, response);
    }
%>

<div class="col-md-8 col-md-offset-2">
    <h1>Choose topic to start:</h1>

    <form method="post" action="/Relay">
        <input type="hidden" name="action" value="Quiz">
        <input type="hidden" name="sub_action" value="choose_topic">

        <% DAO dao = DAO.getQuizDAO();
            ArrayList topics = dao.getTopics();
            Topic aTopic;
            Iterator i = topics.iterator();
            while (i.hasNext()) {
                aTopic = (Topic) i.next();
        %>
        <div class="radio">
            <label for="topic_<%= aTopic.getId()%>">
                <input type="radio" value="<%=aTopic.getId()%>" name="topic_id" id="topic_<%= aTopic.getId()%>"/>
                <%=aTopic.getName()%>
            </label>
        </div>
        <%
            }
        %>
        <input type="submit" value="Start!" class="btn btn-success">
    </form>
</div>
<%@include file="partials/footer.jsp" %>