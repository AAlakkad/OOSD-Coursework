<%@page import="integration.DAO"%>
<%@page import="business.TransferObjects.*"%>
<%@include file="../../partials/header.jsp" %>
<% String id = request.getParameter("id");
    String subAction = "new";
    String name = "", description = "";
    TopicInterface topic = new Topic();
    if (id != null) {
        subAction = "edit";
        // Get Topic from DAO
        DAO dao = DAO.getQuizDAO();
        topic = dao.getTopic(Integer.parseInt(id));
        name = topic.getName();
        description = topic.getDescription();
    }
%>
<h1><a href="./index.jsp">Topics</a> <small>&raquo; <%= subAction%></small></h1>

<div class="col-md-8 col-md-offset-2">
    <form action="/Relay" method="post">
        <input type="hidden" name="action" value="Topics"/>
        <input type="hidden" name="sub-action" value="<%= subAction%>"/>
        <input type="hidden" name="id" value="<%= id%>">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Topic name" value="<%= name%>" />
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" name="description" id="description" placeholder="Topic description"><%=description%></textarea>
        </div>

        <input type="submit" value="Save" class="btn btn-primary"/>
    </form>
</div>

<%@include file="../../partials/footer.jsp" %>