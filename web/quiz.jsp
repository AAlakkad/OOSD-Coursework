<%@page import="business.TransferObjects.QuestionInterface"%>
<%@page import="integration.DAO"%>
<%@include file="partials/header.jsp" %>
<%    Integer questionId = 1;
    DAO dao = DAO.getQuizDAO();
    QuestionInterface question = dao.getQuestion(questionId);
    String title = question.getTitle();
    String answer_1 = question.getAnswer_1();
    String answer_2 = question.getAnswer_2();
    String answer_3 = question.getAnswer_3();
    String answer_4 = question.getAnswer_4();
%>
<h4>Question: <small>(x) from (y)</small></h4>

<h1 class="question-title"><%= title%></h1>

<form method="post" action="/Relay">
    <input type="hidden" name="action" value="Quiz">
    <input type="hidden" name="sub_action" value="question">
    <input type="hidden" name="question_id" value="<%= questionId%>">

    <div class="radio">
        <label for="answer_1">
            <input type="radio" value="1" name="answer" id="answer_1" />
            <%=answer_1%>
        </label>
    </div>

    <div class="radio">
        <label for="answer_2">
            <input type="radio" value="2" name="answer" id="answer_2" />
            <%=answer_2%>
        </label>
    </div>

    <div class="radio">
        <label for="answer_3">
            <input type="radio" value="3" name="answer" id="answer_3" />
            <%=answer_3%>
        </label>
    </div>

    <div class="radio">
        <label for="answer_4">
            <input type="radio" value="4" name="answer" id="answer_4" />
            <%=answer_4%>
        </label>
    </div>

    <input type="submit" value="Submit" class="btn btn-default">
</form>
            
<%@include file="partials/footer.jsp" %>