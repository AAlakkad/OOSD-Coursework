<%@page import="integration.Authentication"%>
<%@page import="business.TransferObjects.Question" %>
<%@page import="business.TransferObjects.QuestionInterface" %>
<%@page import="integration.DAO" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Iterator" %>

<%  Authentication.mustBeAdministrator(request, response);%>

<% String id = request.getParameter("id");
    String subAction = "new";
    Integer topicId = 0;
    Integer difficultyId = 0;
    Integer correctAnswer = 1;
    String title = "";
    String answer_1 = "";
    String answer_2 = "";
    String answer_3 = "";
    String answer_4 = "";
    DAO dao = DAO.getQuizDAO();
    QuestionInterface question = new Question();
    if (id != null) {
        subAction = "edit";
        // Get Question from DAO
        question = dao.getQuestion(Integer.parseInt(id));
        topicId = question.getTopicId();
        difficultyId = question.getDifficultyId();
        correctAnswer = question.getCorrectAnswer();
        title = question.getTitle();
        answer_1 = question.getAnswer_1();
        answer_2 = question.getAnswer_2();
        answer_3 = question.getAnswer_3();
        answer_4 = question.getAnswer_4();
    }
%>

<%@include file="../../partials/header.jsp" %>

<h1><a href="<%=path%>/Relay?action=/admin/questions/index.jsp">Questions</a>
    <small>&raquo; <%= subAction%>
    </small>
</h1>

<div class="col-md-8 col-md-offset-2">
    <form action="<%=path%>/Relay" method="post" class="form-horizontal">
        <input type="hidden" name="action" value="Questions"/>
        <input type="hidden" name="sub-action" value="<%= subAction%>"/>
        <input type="hidden" name="id" value="<%= id%>">

        <div class="form-group">
            <label class="col-md-2" for="title">Title:</label>

            <div class="col-md-10">
                <input type="text" class="form-control" name="title" id="title" placeholder="Question title" value="<%= title%>"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2" for="name">Topic:</label>

            <div class="col-md-10">
                <select class="form-control" name="topic_id" id="topic_id">
                    <%
                        HashMap topics = dao.getTopicsNames();
                        Iterator topicsIterator = topics.keySet().iterator();
                        while (topicsIterator.hasNext()) {
                            Integer _topic_id = (Integer) topicsIterator.next();
                            String _topic_name = (String) topics.get(_topic_id);

                            String selected = topicId == _topic_id ? "selected" : "";
                    %>
                    <option value="<%= _topic_id%>" <%=selected%>><%=_topic_name%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2" for="name">Difficulty:</label>

            <div class="col-md-10">
                <select class="form-control" name="difficulty_id" id="difficulty_id">
                    <%
                        HashMap difficulties = dao.getDifficultiesNames();
                        Iterator difficultiesIterator = difficulties.keySet().iterator();
                        while (difficultiesIterator.hasNext()) {
                            Integer _difficulty_id = (Integer) difficultiesIterator.next();
                            String _difficulty_name = (String) difficulties.get(_difficulty_id);

                            String selected = difficultyId == _difficulty_id ? "selected" : "";
                    %>
                    <option value="<%= _difficulty_id%>" <%=selected%>><%=_difficulty_name%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>
        </div>

        <div class="alert alert-info">Select a <strong>radio box</strong> at the right of each answer to specify the
            correct answer.
        </div>

        <div class="form-group">
            <label class="col-md-2" for="name">Answer 1:</label>

            <div class="input-group">
                <div class="input-group-addon">
                    <input type="radio" name="correct_answer" value="1" checked <% if (correctAnswer == 1) {%>checked<%}%>>
                </div>
                <input type="text" class="form-control col-md-10" name="answer_1" id="answer_1" placeholder="Answer 1" value="<%= answer_1%>"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2" for="name">Answer 2:</label>

            <div class="input-group">
                <div class="input-group-addon">
                    <input type="radio" name="correct_answer" value="2" <% if (correctAnswer == 2) {%>checked<%}%>>
                </div>
                <input type="text" class="form-control col-md-10" name="answer_2" id="answer_2" placeholder="Answer 2" value="<%= answer_2%>"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2" for="name">Answer 3:</label>

            <div class="input-group">
                <div class="input-group-addon">
                    <input type="radio" name="correct_answer" value="3" <% if (correctAnswer == 3) {%>checked<%}%>>
                </div>
                <input type="text" class="form-control col-md-10" name="answer_3" id="answer_3" placeholder="Answer 3" value="<%= answer_3%>"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2" for="name">Answer 4:</label>

            <div class="input-group">
                <div class="input-group-addon">
                    <input type="radio" name="correct_answer" value="4" <% if (correctAnswer == 4) {%>checked<%}%>>
                </div>
                <input type="text" class="form-control col-md-10" name="answer_4" id="answer_4" placeholder="Answer 4" value="<%= answer_4%>"/>
            </div>
        </div>

        <div class="col-md-offset-2">
            <input type="submit" value="Save" class="btn btn-primary pull-left"/>
        </div>
    </form>
    <% if (id != null) {%>
    <form action="<%=path%>/Relay" method="post">
        <input type="hidden" name="action" value="Questions"/>
        <input type="hidden" name="sub-action" value="delete"/>
        <input type="hidden" name="id" value="<%= id%>">
        <input type="submit" value="Delete" class="btn btn-danger pull-right confirm"/>
    </form>
    <% }%>
</div>

<%@include file="../../partials/footer.jsp" %>