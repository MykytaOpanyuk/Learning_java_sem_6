<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Car Rental Companies</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/uikit.css"/>
    <script src="js/uikit.min.js"></script>
    <script src="js/uikit-icons.min.js"></script>
</head>
<body>

<section class="uk-section">
    <div class="uk-container">
        <table class="uk-table uk-table-hover uk-table-divider uk-table-striped">
            <thead>
            <tr>
                <td>ID</td>
                <td>Car Rental Company Name</td>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="Rental_companies" type="java.util.List"--%>
            <c:forEach var="user" items="${Rental_companies}">
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.get_RentalCompany_name()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <form action="<c:url value="/RentalCompany"/>" class="uk-form-horizontal" method="POST">

            <div class="uk-margin">
                <label class="uk-form-label" for="RentalCompany_name">RentalCompany Name:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="RentalCompany_name" type="text" name="RentalCompany_name"
                           placeholder="RentalCompany name...">
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
