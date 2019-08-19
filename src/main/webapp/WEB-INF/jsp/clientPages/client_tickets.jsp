<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="textBundle"/>

<html lang="${locale}">
<head>
    <meta charset="UTF-8">
    <title>Cinema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" href=" https://www.w3schools.com/w3css/4/w3.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<script src="/js/language.js"></script>

<body class="w3-light-grey">
<div class="w3-container w3-teal w3-opacity w3-left-align front-size:20px">
            <div class="w3-dropdown-hover w3-right ">
                <button class="w3-button w3-teal"><fmt:message key="language"/></button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4" style="min-width:70px">
                  <a class="w3-bar-item w3-button w3-teal"
                        onclick="addUrlParameter('locale', 'en')"><fmt:message key="language.en" /></a>
                  <a class="w3-bar-item w3-button  w3-teal"
                        onclick="addUrlParameter('locale', 'ru')"><fmt:message key="language.ru" /></a>
                </div>
            </div>
            <div class="w3-container w3-center w3-padding ">
                <h1><fmt:message key="cinema.project"/></h1>
            </div>
</div>

<div class="w3-container w3-left-align">
    <div class="w3-bar w3-padding-large w3-padding-24">

            <button class="w3-btn w3-teal w3-round-large w3-left" onclick="location.href='/cinema/cabinet'">
                            <fmt:message key="back.cabinet"/>
            </button>
            <button class="w3-btn  w3-teal w3-border w3-border-redl w3-round-large w3-right"
                onclick="location.href='/cinema/logout'">
                <fmt:message key="logout.button"/>
            </button>
     </div>
</div>
<div class="w3-container w3-center">
    <footer class="w3-container w3-teal w3-round-large" style="width:100% ">
        <h4><b><fmt:message key="ticket.list"/></b></h4>
    </footer>
</div>
<div><p></p></div>
<div class="w3-container w3-text-teal">
    <table class="w3-table w3-small w3-bordered  ">
        <tr>
            <th><fmt:message key="ticket.number"/></th>
            <th><fmt:message key="movie.name"/></th>
            <th><fmt:message key="week.day"/></th>
            <th><fmt:message key="movie.time"/></th>
            <th><fmt:message key="hall"/></th>
            <th><fmt:message key="hall.row"/></th>
            <th><fmt:message key="hall.place"/></th>
            <th><fmt:message key="price"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        <tr>
            <c:forEach var="ticket" items="${tickets}" varStatus = "loopStatus">
                <td><br><c:out value="${ticket.ticketId}"/></td>
                <td><br><c:out value="${ticket.movieName}"/></td>
                <td><br><c:out value="${ticket.weekDay}"/></td>
                <td><br><c:out value="${ticket.scheduleTime}"/></td>
                <td><br><c:out value="${ticket.hallName}"/></td>
                <td><br><c:out value="${ticket.placeRow}"/></td>
                <td><br><c:out value="${ticket.placeNumber}"/></td>
                <td><br><c:out value="${ticket.ticketPrice}"/></td>
                <td class="w3-left">
                <button class="w3-button w3-border w3-padding-small"
                     onclick="location.href='/client/deleteticket?ticket_id=${ticket.ticketId}'">
                     <fmt:message key="delete"/>
                </button>
            </tr>
            </c:forEach>
        </tr>
    </table>

</div>
</body>
</html>