<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cars</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/uikit.css"/>
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
                <td>Car Name</td>
                <td>Car description</td>
                <td>Car rental company id</td>
                <td>Car cost</td>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="cars" type="java.util.List"--%>
            <c:forEach var="car" items="${cars}">
                <tr>
                    <td>${car.getId()}</td>
                    <td>${car.getName()}</td>
                    <td>${car.getDescription()}</td>
                    <td>${car.getRentalCompany()}</td>
                    <td>${car.getCost()}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<section class="uk-section">
    <div class="uk-container">
        <form action="<c:url value="/car"/>" class="uk-form-horizontal" method="POST">

            <div class="uk-margin">
                <label class="uk-form-label" for="car_name">Car name:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="car_name" type="text" name="car_name"
                           placeholder="Car name...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="car_description">Car description:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="car_description" type="text" name="car_description"
                           placeholder="Car description...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="CarRentalCompany">Rental company id:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="CarRentalCompany" type="text" name="CarRentalCompany"
                           placeholder="Car rental company id...">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="car_cost">Car cost:</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="car_cost" type="text" name="car_cost"
                           placeholder="Car cost...">
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
