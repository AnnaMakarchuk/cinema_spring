<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="textBundle"/>

<html lang="${locale}>
<head>
    <meta charset="UTF-8">
    <title>Cinema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<script src="/js/language.js"></script>

<body class="w3-light-grey">
<div class="w3-container w3-teal w3-opacity w3-left-align front-size:20px">
            <div class="w3-dropdown-hover w3-right ">
                <button class="w3-button w3-teal"><fmt:message key="language"/></button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4" style="min-width:70px">
                  <a class="w3-bar-item w3-button w3-teal" onclick="languageChange('locale', 'en')"><fmt:message key="language.en" /></a>
                  <a class="w3-bar-item w3-button  w3-teal" onclick="languageChange('locale', 'ru')"><fmt:message key="language.ru" /></a>
                </div>
            </div>
            <div class="w3-container w3-center w3-padding ">
                <h1><fmt:message key="cinema.project"/></h1>
            </div>
</div>

<div class="w3-container w3-right-align">
    <p> <div class="w3-bar w3-padding-large w3-padding-24">
            <button class="w3-btn w3-border w3-teal w3-round-large w3-left" button onclick="location.href='/schedule?day=monday'">
                <fmt:message key="schedule"/>
            </button>

             <button class="w3-btn w3-border w3-teal w3-round-large w3-left" onclick="location.href='/login'">
                            <fmt:message key="login"/>
                        </button>

            <c:choose>
                <c:when test="${user == null}">
                        <button onclick="document.getElementById('id02').style.display='block'" class="w3-button w3-teal w3-round-large w3-right-align">
                            <fmt:message key="login.button"/>
                        </button>
                        <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-teal w3-round-large w3-right-align">
                                <fmt:message key="register.button"/>
                           </button>
                </c:when>
                <c:otherwise>
                        <button class="w3-btn w3-border w3-teal w3-round-large" onclick="submitTickets('boughttickets')">
                            <fmt:message key="buy.selected.tickets"/>
                        </button>

                        <button class="w3-btn w3-border w3-teal w3-round-large w3-right-align" onclick="location.href='/cinema/cabinet'">
                            <fmt:message key="back.cabinet"/>
                        </button>
                        <button class="w3-btn w3-border w3-teal w3-round-large w3-right-align w3-border-red" onclick="location.href='/cinema/logout'">
                            <fmt:message key="logout.button"/>
                        </button>
                </c:otherwise>
            </c:choose>




                 <div id="id02" class="w3-modal">
                    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                        <div class="w3-center"><br>
                            <span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
                        </div>
                        <form class="w3-container" method="POST" action="/cinema/login">
                            <div class="w3-section w3-left-align">
                                <label><b><fmt:message key="login"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter.login"/>" name="login" required>

                                <label><b><fmt:message key="password"/></b></label>
                                <input class="w3-input w3-border" type="password" placeholder="<fmt:message key="enter.password"/>" name="password" required>

                                <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding" type="submit">
                                    <fmt:message key="login.button"/>
                                </button>
                            </div>
                        </form>
                    </div>
                 </div>
                 <div id="id01" class="w3-modal">
                    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                        <div class="w3-center"><br>
                            <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
                        </div>

                        <form class="w3-container" method="POST" action="/cinema/register">
                            <div class="w3-section w3-left-align">
                                <label><b><fmt:message key="name"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter.name"/>" name="name" required>

                                <label><b><fmt:message key="surname"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter.surname"/>" name="surname" required>

                                <label><b><fmt:message key="login"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter.login"/>" name="login" required>

                                <label><b><fmt:message key="email"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter.email"/>" name="email" required>

                                <label><b><fmt:message key="password"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="password" placeholder="<fmt:message key="enter.password"/>" name="password" required>

                                <form action="">
                                    <input type="radio" name="gender" value="male"><fmt:message key="male"/><br>
                                    <input type="radio" name="gender" value="female"><fmt:message key="female"/><br>
                                </form>

                                <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding" type="submit">
                                    <fmt:message key="register.button"/></button>
                            </div>
                        </form>
                    </div>
                 </div>
    </p>
</div>

<div class="w3-container w3-center w3-text-teal">
    <h1><b><fmt:message key="movie.list"/></b></h1>
</div>
<div class="w3-container w3-left-align">
     <div class="w3-bar w3-padding-large w3-padding-24">
        <c:forEach var="movie" items="${movies}">
        <div class="w3-card-4 w3-left" style="width:100%;">
            <header class="w3-container w3-teal">
              <h1><b><c:out value="${movie.movieName}"/></b></h1>
            </header>

            <div class="w3-container">
                <p><b><c:out value="${movie.movieGenre}"/></b></p>
                              <p><c:out value="${movie.movieDescription}"/></p>
                              <p><fmt:message key="duration"/><c:out value="${movie.movieDuration}"/> <fmt:message key="min"/></p>
            </div>

            <footer class="w3-container w3-teal">
                <h5><c:out value="${movie.ageLimit}"/> + </h5>
            </footer>
        </div>
        <div class="w3-container w3-large">
            <div><p></p></div>
        </div>
        </c:forEach>
     </div>
</div>
</body>
</html>