<%@ page import="static javax.servlet.RequestDispatcher.ERROR_MESSAGE" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>Error</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.4/css/uikit.min.css"/>
    <script src="../../js/uikit.js"></script>
    <script src="../../js/uikit-icons.js"></script>
</head>
<body>
<section class="uk-section">
    <div class="uk-container">
        <div class="uk-text-danger uk-text-center">
            <%=request.getAttribute(ERROR_MESSAGE)%>
        </div>
    </div>
</section>
</body>
</html>

