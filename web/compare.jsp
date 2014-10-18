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

    <form method="post" action="/Relay">
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
        <input type="submit" value="Compare!" class="btn btn-success">
    </form>
</div>
<%@include file="partials/footer.jsp" %>