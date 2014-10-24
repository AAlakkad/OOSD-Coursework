<%@page import="business.TransferObjects.UserInterface" %>
<% String path = request.getContextPath().toString(); %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="author" content="Ammar Alakkad">
        <link rel="stylesheet" href="<%= path%>/assets/bootstrap.min.css"/>
        <link rel="stylesheet" href="<%= path%>/assets/custom.css"/>
        <title>Quiz Application</title>
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>

        <div class="navbar navbar-default" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<%=path%>">Quiz Application</a>
                </div>
                <% UserInterface user = (UserInterface) session.getAttribute("user"); %>
                <%
                    if (user != null && user.exists()) {
                %>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <%
                            if (user.isAdministrator()) {
                                // Load administrator menu
                        %>
                        <li><a href="<%=path%>/Relay?action=admin/topics/index.jsp">Topics</a></li>
                        <li><a href="<%=path%>/Relay?action=admin/questions/index.jsp">Questions</a></li>
                            <%
                            } else {
                                // Load contestant menu
                            %>
                        <li><a href="<%=path%>/Relay?action=topic.jsp">Start quiz</a></li>
                        <li><a href="<%=path%>/Relay?action=compare.jsp">Compare your result</a></li>
                            <%
                                }
                            %>
                    </ul>
                </div>
                <%
                    }
                %>

            </div>
        </div>

        <div class="container">