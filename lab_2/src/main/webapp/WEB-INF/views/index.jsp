<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>Main</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.4/css/uikit.min.css"/>
    <script src="../../js/uikit.js"></script>
    <script src="../../js/uikit-icons.js"></script>
</head>
<body>
<header>
    <div class="uk-grid-small uk-child-width-expand@s uk-text-center" uk-grid>
        <div>
            <div class="uk-card uk-card-default uk-card-body">
                <a class="uk-active" href="Rental_company">Companies</a>
            </div>
        </div>
        <div>
            <div class="uk-card uk-card-default uk-card-body">
                <a class="uk-active" href="agent">Agents</a>
            </div>
        </div>
        <div>
            <div class="uk-card uk-card-default uk-card-body">
                <a href="order">Orders</a>
            </div>
        </div>
        <div>
            <div class="uk-card uk-card-default uk-card-body">
                <a href="car">Cars</a>
            </div>
        </div>
    </div>
</header>

<section class="uk-section">
    <div class="uk-container">
        <h2 class="uk-heading-medium uk-text-center">Welcome, ${cookie.get("username").value}</h2>
    </div>
</section>
</body>
</html>
