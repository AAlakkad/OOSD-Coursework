<% String baseUrl = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf(request.getServletPath()));%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="author" content="Ammar Alakkad">
        <link rel="stylesheet" href="<%= baseUrl%>/assets/bootstrap.min.css"/>
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
                    <a class="navbar-brand" href="<%= baseUrl%>">Quiz Application</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Must</a></li>
                        <li><a href="#about">Be</a></li>
                        <li><a href="#contact">Changed</a></li>
                        <li><a>According</a></li>
                        <li><a>To</a></li>
                        <li><a>Session</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="container">