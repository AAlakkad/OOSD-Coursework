<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.TransferObjects.Topic"%>
<%@page import="integration.DAO"%>
<%@include file="../../partials/header.jsp" %>

<h1>Topics <a href="./form.jsp" class="btn btn-sm btn-default">New Topic</a></h1>


<table class="table table-bordered table-hover">
    <thead>
        <tr>
            <th width="5%">#</th>
            <th>Name</th>
            <th width="15%">Actions</th>
        </tr>
    </thead>
    <tbody>

        <%    DAO dao = DAO.getQuizDAO();
            ArrayList topics = dao.getTopics();
            Topic aTopic;
            Iterator i = topics.iterator();
            while (i.hasNext()) {
                aTopic = (Topic) i.next();
        %>
        <tr>
            <td><%=aTopic.getId()%></td>
            <td><%=aTopic.getName()%></td>
            <td>
                <div class="btn-group btn-group-justified">
                    <a href="./form.jsp?id=<%=aTopic.getId()%>" class="btn btn-success">Edit</a>
                    <a href="#<%=aTopic.getId()%>" class="btn btn-default">Delete</a>
                </div>
            </td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>

<%@include file="../../partials/footer.jsp" %>