<%@page import="integration.DAO"%>
<%@page import="business.TransferObjects.TopicInterface"%>
<%@include file="partials/header.jsp" %>
<%    String score = session.getAttribute("quizScore") != null ? session.getAttribute("quizScore").toString() : "0";
    Integer topicId = Integer.parseInt(session.getAttribute("quizTopicId").toString());
    DAO dao = DAO.getQuizDAO();
    TopicInterface topic = dao.getTopic(topicId);
%>

<h1><small>Your score is: </small><%= score%></h1>
<h2>You're the (first/second/third) score in <em><%= topic.getName()%></em> topic</h2>
<%@include file="partials/footer.jsp" %>