<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Agents</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.4/css/uikit.min.css"/>
    <script src="../../js/uikit.js"></script>
    <script src="../../js/uikit-icons.js"></script>
</head>
<body>

<section class="uk-section">
    <div class="uk-container">
        <table class="uk-table uk-table-hover uk-table-divider uk-table-striped">
            <thead>
            <tr>
                <td>ID</td>
                <td>Agent Name</td>
                <td>Company Id</td>

            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="agents" type="java.util.List"--%>
            <c:forEach var="user" items="${agents}">
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.getAgentName()}</td>
                    <td>${user.company_id()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <form action="<c:url value="/agent"/>" class="uk-form-horizontal" method="POST">

            <div class="uk-margin">
                <label class="uk-form-label" for="agent_name">Agent name:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="agent_name" type="text" name="agent_name"
                           placeholder="Agent name...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="company_id">Company id:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="company_id" type="text" name="company_id"
                           placeholder="company id...">
                </div>
            </div>

            <div class="uk-margin">
                <button class="uk-button uk-button-default" type="submit">Submit</button>
            </div>
        </form>
    </div>

    <div class="uk-container">
        <a href="index.jsp">Home</a>
    </div>
</section>

</body>
</html>
