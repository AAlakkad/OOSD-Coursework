<%@page import="java.util.Iterator"%>
<%@page import="business.TransferObjects.Topic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="integration.DAO"%>
<%@include file="partials/header.jsp" %>

<h1>Choose topic to start:</h1>

<form method="post" action="/Relay">
    <input type="hidden" name="action" value="Quiz">

    <%    DAO dao = DAO.getQuizDAO();
        ArrayList topics = dao.getTopics();
        Topic aTopic;
        Iterator i = topics.iterator();
        while (i.hasNext()) {
            aTopic = (Topic) i.next();
    %>
    <div class="radio">
        <label for="topic_id">
            <input type="radio" value="<%=aTopic.getId()%>" name="topic_id" id="topic_id" />
            <%=aTopic.getName()%>
        </label>
    </div>
    <%
        }
    %>

    <input type="submit" value="Start!" class="btn btn-success">
</form>
<%@include file="partials/footer.jsp" %>