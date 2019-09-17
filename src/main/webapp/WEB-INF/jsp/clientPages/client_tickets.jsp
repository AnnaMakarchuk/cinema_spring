<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="${locale}">
<head>
    <meta charset="UTF-8">
    <title>Cinema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<script>
    function addUrlParameter(name, value) {
      var searchParams = new URLSearchParams(window.location.search)
      searchParams.set(name, value)
      window.location.search = searchParams.toString()
    }
</script>

<body class="w3-light-grey">
<div class="w3-container w3-teal w3-opacity w3-left-align front-size:20px">
            <div class="w3-dropdown-hover w3-right ">
             <button class="w3-button w3-teal"><spring:message code="language"/></button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4" style="min-width:70px">
                  <a class="w3-bar-item w3-button w3-teal" onclick="addUrlParameter('locale', 'en')"> <spring:message code="language.en" /></a>
                  <a class="w3-bar-item w3-button  w3-teal" onclick="addUrlParameter('locale', 'ru')"> <spring:message code="language.ru" /></a>
                </div>
            </div>
            <div class="w3-container w3-center w3-padding ">
                <h1><spring:message code="cinema.project"/></h1>
            </div>
</div>

<div class="w3-container w3-left-align">
    <div class="w3-bar w3-padding-large w3-padding-24">
            <button class="w3-btn w3-teal w3-round-large w3-left" onclick="location.href='/cabinet'">
                            <spring:message code="back.cabinet"/>
            </button>
            <button class="w3-btn  w3-teal w3-border w3-border-redl w3-round-large w3-right"
                onclick="location.href='/logout'">
                <spring:message code="logout.button"/>
            </button>
     </div>
</div>
<div class="w3-container w3-center">
    <footer class="w3-container w3-teal w3-round-large" style="width:100% ">
        <h4><b><spring:message code="ticket.list"/></b></h4>
    </footer>
</div>
<div><p></p></div>
<div class="w3-container w3-text-teal">
    <table class="w3-table w3-small w3-bordered  ">
        <tr>
            <th><spring:message code="ticket.number"/></th>
            <th><spring:message code="movie.name"/></th>
            <th><spring:message code="week.day"/></th>
            <th><spring:message code="movie.time"/></th>
            <th><spring:message code="hall"/></th>
            <th><spring:message code="hall.row"/></th>
            <th><spring:message code="hall.place"/></th>
            <th><spring:message code="price"/></th>
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
            </tr>
            </c:forEach>
        </tr>
    </table>

</div>
</body>
</html>