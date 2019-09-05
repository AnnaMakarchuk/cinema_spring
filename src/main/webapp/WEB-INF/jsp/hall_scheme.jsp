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

<div class="w3-container w3-right-align">
    <p> <div class="w3-bar w3-padding-large w3-padding-24">
            <button class="w3-btn w3-border w3-teal w3-round-large w3-left" onclick="location.href='/schedule'">
                <spring:message code="schedule"/>
            </button>

            <c:choose>
                <c:when test="${user == null}">
                        <button onclick="document.getElementById('id02').style.display='block'" class="w3-button w3-teal w3-round-large w3-right-align">
                            <spring:message code="login.button"/>
                        </button>
                        <div id="id02" class="w3-modal">
                                <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                                    <div class="w3-center"><br>
                                        <span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
                                    </div>
                                    <form class="w3-container" method="POST" action="/cinema/login">
                                        <div class="w3-section w3-left-align">
                                            <label><b><fmt:message key="login"/></b></label>
                                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<spring:message code="enter.login"/>" name="login" required>

                                            <label><b><fmt:message key="password"/></b></label>
                                            <input class="w3-input w3-border" type="password" placeholder="<spring:message code="enter.password"/>" name="password" required>

                                            <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding" type="submit">
                                                <spring:message code="login.button"/>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                        </div>

                        <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-teal w3-round-large w3-right-align">
                                <spring:message code="register.button"/>
                        </button>
                            <div id="id01" class="w3-modal">
                                  <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                                         <div class="w3-center"><br>
                                              <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright"
                                              title="Close Modal">&times;</span>
                                         </div>
                                            <div class="w3-container">
											 <div class="w3-section w3-left-align">
                                                  <label><b><spring:message code="name"/></b></label>
                                                  <input class="w3-input w3-border w3-margin-bottom" id = "name" type="text" placeholder="<spring:message code="enter.name"/>" name="userName" required>

                                                  <label><b><spring:message code="surname"/></b></label>
                                                  <input class="w3-input w3-border w3-margin-bottom" id = "surname"  type="text" placeholder="<spring:message code="enter.surname"/>" name="userSurname" required>

                                                  <label><b><spring:message code="login"/></b></label>
                                                  <input class="w3-input w3-border w3-margin-bottom" id = "login" type="text" placeholder="<spring:message code="enter.login"/>" name="userLogin" required>

                                                  <label><b><spring:message code="email"/></b></label>
                                                  <input class="w3-input w3-border w3-margin-bottom" id = "email" type="text" placeholder="<spring:message code="enter.email"/>" name="userEMailAddress" required>

                                                  <label><b><spring:message code="password"/></b></label>
                                                  <input class="w3-input w3-border w3-margin-bottom" id = "password" type="password" placeholder="<spring:message code="enter.password"/>" name="userPassword" required>


                                                       <input type="radio" name="gender" value="MALE"><spring:message code="male"/><br>
                                                       <input type="radio" name="gender" value="FEMALE"><spring:message code="female"/><br>


                                                  <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding" onclick="submitUser('register')">
                                                        <spring:message code="register.button"/></button>
                                             </div>
											</div>
                                  </div>
                            </div>
                </c:when>
                <c:otherwise>
                        <button class="w3-btn w3-border w3-teal w3-round-large" onclick="submitTickets('boughttickets')">
                            <spring:message code="buy.selected.tickets"/>
                        </button>
                
                        <button class="w3-btn w3-border w3-teal w3-round-large w3-right-align" onclick="location.href='/cabinet'">
                            <spring:message code="back.cabinet"/>
                        </button>
                        <button class="w3-btn w3-border w3-teal w3-round-large w3-right-align w3-border-red" onclick="location.href='/logout'">
                            <spring:message code="logout.button"/>
                        </button>
                </c:otherwise>
            </c:choose>
    </p>
</div>

<div class="w3-container w3-center w3-text-teal">
    <p class="w3-left"><h1><b><c:out value="${schedule.movie.movieName}"/></b></h1></p>
    <i class="fa fa-calendar" style="font-size:24px"><c:out value="${schedule.weekDay}"/> </i>
    <i class="fa fa-clock-o" style="font-size:24px"><c:out value="${schedule.time}"/> </i>
</div>

<div><br></div>

<div class="w3-bar w3-center">
        <c:forEach var = "r" begin = "1" end = "${hall.maxRow}">
                ${r}
                <c:forEach var = "c" begin = "1" end = "${hall.maxPlacesInRow}">
                        <button id="${r}-${c}" class="w3-btn w3-center w3-border w3-border-teal w3-round-large chair" onclick="submitButtonStyle(this, ${r}, ${c})" >
                                <div class="button_text">
                                        ${c}
                                </div>
                                <div class="button_price"> <c:out value="${hall.prices[r].price}" /> </div>
                        </button>
                </c:forEach>
            </p>
        </c:forEach>
</div>

<style>
    .chair {
        height: 50px;
        width: 50px;
    }

    .chair:hover .button_text {
        color: coral;
        opacity: 1;
    }

    .button_price {
        opacity: 0;
        text-align: center;
    }
    .chair:hover .button_price {
        opacity: 1;
    }
</style>

<script>

    function addUrlParameter(name, value) {
      var searchParams = new URLSearchParams(window.location.search)
      searchParams.set(name, value)
      window.location.search = searchParams.toString()
    }

    function submitUser(url) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", url);
        xhr.setRequestHeader('Content-Type', 'application/json');
        var body = new Object();
                body["userName"]=document.getElementById("name").value;
                body["userSurname"]=document.getElementById("surname").value;
                body["userLogin"]=document.getElementById("login").value;
                body["userEMailAddress"]=document.getElementById("email").value;
                body["userPassword"]=document.getElementById("password").value;
            var radios = document.getElementsByName('gender');
            for (var i = 0, length = radios.length; i < length; i++)
            {
             if (radios[i].checked)
             {
              body["gender"]=radios[i].value;
              break;
             }
            }

        xhr.send(JSON.stringify(body));
        xhr.onload = function() {
             confirm("User was registered in system");
             window.location.reload();
        };
    }

    var occupied = JSON.parse('${occupiedPlaces}');

    document.addEventListener('DOMContentLoaded', changePlaces());

    function changePlaces() {
        Object.keys(occupied).forEach(function(key) {
            var tmp = occupied[key];
            var btn = document.getElementById(tmp.row+"-"+tmp.place);
            if (btn != NaN) {
                btn.disabled = true;
            }
        });

    }

    places = [];

    function submitButtonStyle(_this, row, place) {
        var color_one = "#009688";
        var bgcolor = _this.style.backgroundColor;
        _this.style.backgroundColor = color_one;
        console.log(places);
        if(bgcolor == _this.style.backgroundColor) {
            _this.style.backgroundColor = "#f1f1f1";
            places = places.filter(place => (place["row"] != row && place["place"] != place));
        } else {
            places.push({"row": row, "place": place});
        }
        console.log(places);
    }

    function submitTickets(url) {
        console.log(places);
        if (places.length > 0) {
            console.log("sending post");
            var xhr = new XMLHttpRequest();
            xhr.open("POST", url);
            xhr.setRequestHeader('Content-Type', 'application/json');
            var body = new Object();
            body["scheduleId"]="${schedule.id}";
            body["places"]=Object.values(places);

            xhr.send(JSON.stringify(body));
            xhr.onload = function() {
                confirm("Ticket was bought");
                window.location.reload();
            };
        }
    }

</script>

</body>
</html>