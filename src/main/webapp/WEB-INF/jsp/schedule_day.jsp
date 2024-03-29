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
                                             </div> <div id="error" class="w3-modal">
                                                    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                                                         <div class="w3-center"><br>
                                                               <span onclick="document.getElementById('error').style.display='none'"
                                                                class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
                                                         </div>
                                                         <div class="w3-container">
                                                              <div id="responseHere" class="w3-section w3-left-align">
                                                                  <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding" onclick="submitMovie('addmovie')">
                                                                  <spring:message code="adminadd.movie"/></button>
                                                              </div>
                                                         </div>
                                                    </div>
                                             </div>
                                       </div>
                           </c:when>
                <c:otherwise>
                        <button class="w3-btn w3-border w3-teal w3-round-large" onclick="submitTickets('boughttickets')">
                            <spring:message code="buy.selected.tickets"/>
                        </button>

                        <button class="w3-btn w3-border w3-teal w3-round-large w3-right-align" onclick="location.href='/cinema/cabinet'">
                            <spring:message code="back.cabinet"/>
                        </button>
                        <button class="w3-btn w3-border w3-teal w3-round-large w3-right-align w3-border-red" onclick="location.href='/cinema/logout'">
                            <spring:message code="logout.button"/>
                        </button>
                </c:otherwise>
            </c:choose>
    </p>
</div>

<div class="w3-container w3-center">
    <footer class="w3-container w3-teal w3-round-large" style="width:100% ">
        <h4><b><spring:message code="hall.name"/></b></h4>
    </footer>
</div>
<br>

<div class="w3-container">
  <div id="day_panel" class="w3-bar w3-teal w3-text-white">
    <a id="monday" href="?day=monday" class="w3-bar-item w3-button myButton" onclick="submitButtonStyle('monday')" ><spring:message code="monday"/></a>
    <a id="tuesday" href="?day=tuesday" class="w3-bar-item w3-button " onclick="submitButtonStyle('tuesday')"><spring:message code="tuesday"/></a>
    <a id="wednesday" href="?day=wednesday" class="w3-bar-item w3-button " onclick="submitButtonStyle('wednesday')"><spring:message code="wednesday"/></a>
    <a id="thursday" href="?day=thursday" class="w3-bar-item w3-button" onclick="submitButtonStyle('thursday')"><spring:message code="thursday"/></a>
    <a id="friday" href="?day=friday" class="w3-bar-item w3-button " onclick="submitButtonStyle('friday')"><spring:message code="friday"/></a>
    <a id="saturday" href="?day=saturday" class="w3-bar-item w3-button " onclick="submitButtonStyle('saturday')"><spring:message code="saturday"/></a>
    <a id="sunday" href="?day=sunday" class="w3-bar-item w3-button " onclick="submitButtonStyle('sunday')"><spring:message code="sunday"/></a>
  </div>

<div class="w3-container w3-text-teal">
    <p class="w3-left"><spring:message code="session"/><c:out value="${weekday}" /><spring:message code="click.message"/></p>
    <table class="w3-table w3-bordered">
        <tr>
            <th><spring:message code="movie.name"/></th>
            <th><spring:message code="movie.time"/></th>
        </tr>
        <tr>
            <c:forEach var="schedule" items="${schedules}">
                <td><br><c:out value="${schedule.movieName}"/></td>
                <c:forEach var="time" items="${schedule.timeList}">
                    <td class="w3-left"><button class="w3-button w3-border w3-round-large w3-section w3-padding" button onclick="location.href='/hallscheme?schedule_id=${time.scheduleId}'">
                        <c:out value="${time.time}"/></button></td>
                   </td>
            </c:forEach>
            </tr>
        </c:forEach>
    </table>
</div>

<script>

    function submitButtonStyle(day) {
    console.log(day);
    document.getElementById(day).style.background='grey';
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
             if (xhr.status == 200) {
             confirm("User was registered in system");
                 window.location.reload();
             }
            if (xhr.status == 400) {
                document.getElementById("error").style.display = "block";
                document.getElementById("responseHere").innerHTML = xhr.response;
                console.log(xhr.status);
            }
        };
    }
</script>
</body>
</html>