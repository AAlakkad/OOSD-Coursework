<%@page import="integration.DAO"%>
<%@page import="business.TransferObjects.*"%>
<%@include file="../../partials/header.jsp" %>
<% String id = request.getParameter("id");
    String subAction = "new";
    String title = "";
    QuestionInterface question = new Question();
    if (id != null) {
        subAction = "edit";
        // Get Question from DAO
        DAO dao = DAO.getQuizDAO();
        question = dao.getQuestion(Integer.parseInt(id));
        title = question.getTitle();
    }
%>
<h1><a href="./index.jsp">Questions</a> <small>&raquo; <%= subAction%></small></h1>

<div class="col-md-8 col-md-offset-2">
    <form action="/Relay" method="post">
        <input type="hidden" name="action" value="Questions"/>
        <input type="hidden" name="sub-action" value="<%= subAction%>"/>
        <input type="hidden" name="id" value="<%= id%>">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Question name" value="<%= title%>" />
        </div>

        <input type="submit" value="Save" class="btn btn-primary pull-left"/>
    </form>
    <% if (id != null) {%>
    <form action="/Relay" method="post">
        <input type="hidden" name="action" value="Questions"/>
        <input type="hidden" name="sub-action" value="delete"/>
        <input type="hidden" name="id" value="<%= id%>">
        <input type="submit" value="Delete" class="btn btn-danger pull-right confirm" />
    </form>
    <% }%>
</div>

<%@include file="../../partials/footer.jsp" %>