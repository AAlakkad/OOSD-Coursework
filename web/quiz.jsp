<%@page import="integration.Authentication"%>
<%@page import="business.TransferObjects.QuestionInterface" %>
<%@page import="integration.DAO" %>
<%  Authentication.mustBeLoggedIn(request, response);%>
<%
    if (session.getAttribute("quizQuestion") == null) {
        response.sendRedirect("topic.jsp");
        return;
    }
    QuestionInterface question = (QuestionInterface) session.getAttribute("quizQuestion");
    Integer questionId = question.getId();
    String title = question.getTitle();
    String answer_1 = question.getAnswer_1();
    String answer_2 = question.getAnswer_2();
    String answer_3 = question.getAnswer_3();
    String answer_4 = question.getAnswer_4();
    String counter = session.getAttribute("quizCounter") != null ? session.getAttribute("quizCounter").toString() : "1";

    Boolean answersDiscarded = false;
    if (session.getAttribute("answersDiscarded") != null) {
        answersDiscarded = Boolean.parseBoolean(session.getAttribute("answersDiscarded").toString());
    }
%>

<%@include file="partials/header.jsp" %>

<div class="col-md-8 col-md-offset-2">
    <h4>Question:
        <small>(<%= counter%>) from (<%= DAO.quizQuestions%>)</small>
    </h4>

    <h1 class="question-title"><%= title%>
    </h1>

    <% if (session.getAttribute("error") != null) {%>
    <div class="alert alert-danger">
        <%= session.getAttribute("error").toString()%>
    </div>
    <% session.removeAttribute("error");
        }%>

    <form method="post" action="Relay">
        <input type="hidden" name="action" value="Quiz">
        <input type="hidden" name="sub_action" value="submit_answer">
        <input type="hidden" name="question_id" value="<%= questionId%>">
        <% if (answer_1 != null) {%>
        <div class="radio">
            <label for="answer_1">
                <input type="radio" value="1" name="answer" id="answer_1"/>
                <%=answer_1%>
            </label>
        </div>
        <% } %>
        <% if (answer_2 != null) {%>
        <div class="radio">
            <label for="answer_2">
                <input type="radio" value="2" name="answer" id="answer_2"/>
                <%=answer_2%>
            </label>
        </div>
        <% } %>
        <% if (answer_3 != null) {%>
        <div class="radio">
            <label for="answer_3">
                <input type="radio" value="3" name="answer" id="answer_3"/>
                <%=answer_3%>
            </label>
        </div>
        <% } %>
        <% if (answer_4 != null) {%>
        <div class="radio">
            <label for="answer_4">
                <input type="radio" value="4" name="answer" id="answer_4"/>
                <%=answer_4%>
            </label>
        </div>
        <% }%>
        <div class="col-md-6">
            <input type="submit" value="Submit" class="btn btn-default">
        </div>
    </form>
    <% if (!answersDiscarded) {%>

    <form method="post" action="Relay">
        <input type="hidden" name="action" value="Quiz">
        <input type="hidden" name="sub_action" value="discard_answers">
        <input type="hidden" name="question_id" value="<%= questionId%>">
        <div class="col-md-6">
            <input type="submit" value="Discard 2 answers" class="btn btn-warning pull-right">
        </div>

    </form>
    <br><br>
    <div class="clearfix"></div>
    <div class="alert alert-warning" role="alert">Using the <strong>discard 2 answers option</strong> will result in having half a point of the question.</div>
    <% }%>
</div>
<%@include file="partials/footer.jsp" %>