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
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
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
    <div class="w3-bar w3-padding-large w3-padding-24">

       <button class="w3-btn w3-teal w3-border w3-round-large w3-left" onclick="location.href='/'">
                <spring:message code="on.main.page"/>
       </button>

                        <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-teal w3-round-large w3-right-align">
                                <spring:message code="update.button"/>
                        </button>
                            <div id="id01" class="w3-modal">
                                  <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                                         <div class="w3-center"><br>
                                              <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright"
                                              title="Close Modal">&times;</span>
                                         </div>
                                            <div class="w3-container">
											 <div class="w3-section w3-left-align">
											      <label><b><spring:message code="new.login"/></b></label>
                                                  <input class="w3-input w3-border w3-margin-bottom" id = "login" type="text" placeholder="<spring:message code="enter.login"/>" name="userLogin" required>

                                                  <label><b><spring:message code="new.password"/></b></label>
                                                  <input class="w3-input w3-border" id = "password"  type="password" placeholder="<spring:message code="enter.password"/>" name="userPassword" required>

                                                  <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding" onclick="updateUser('cabinet/update')">
                                                        <spring:message code="update.button"/></button>
                                             </div>
											</div>
                                  </div>
                            </div>

       <button class="w3-btn w3-teal w3-border w3-border-red w3-round-large w3-right-align" onclick="location.href='/cinema/logout'">
           <spring:message code="logout.button"/>
       </button>

      </div>
</div>

<div class="w3-container w3-right-align">
    <p> <div class="w3-bar w3-padding w3-padding-12">
    <c:choose>
           <c:when test="${user.userRole.userRole == 'client'}">
               <div class="w3-container w3-center">
                   <footer class="w3-container w3-teal w3-round-large" style="width:100% ">
                       <h3><b><spring:message code="client.cabinet"/></b></h3>
                   </footer>
               </div>
           </c:when>
           <c:when test="${user.userRole.userRole == 'administrator'}">
                <div class="w3-container w3-center w3-text-teal">
                     <h3><b><spring:message code="admin.cabinet"/></b></h3>
                </div>
           </c:when>
    </c:choose>
</div>

<div><p></p></div>

<form class="w3-container w3-card-4 w3-light-grey w3-text-teal" style="width:50%;margin:auto">
     <div class="w3-row w3-section w3-left-align">
        <h4><b><spring:message code="user.name"/></b></h4>
        <p><div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user">&nbsp</i>
        </div><h3><b><c:out value="${user.userName}"/>&nbsp<c:out value="${user.userSurname}"/></b></h3>
        </p>
     </div>
    <div class="w3-row w3-section w3-left-align">
        <h4><b><spring:message code="user.email"/></b></h4>
        <p><div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-envelope-o">&nbsp</i>
        </div><h3><b><c:out value="${user.userEMailAddress}"/></b></h3>
        </p>
    </div>
    <div class="w3-row w3-section w3-left-align">
        <h4><b><spring:message code="user.gender"/></b></h4>
        <p><div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-male">&nbsp</i>
        </div><h3><b><c:out value="${user.gender}"/></b></h3>
    </div>
 </form>

<div class="w3-container w3-right-align">
    <p> <div class="w3-bar w3-padding w3-padding-12">
    <c:choose>
           <c:when test="${user.userRole.userRole == 'client'}">
                   <div class="w3-row w3-section w3-center">
                            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large w3-left-align" onclick="location.href='/client/tickets'">
                                    <spring:message code="show.ticket"/>
                            </button>
                            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large w3-left-align" onclick="location.href='/schedule'">
                                     <spring:message code="buy.ticket"/>
                            </button>
           </c:when>
            <c:when test="${user.userRole.userRole == 'administrator'}">
                   <div class="w3-row w3-section w3-center">
                            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large" onclick="location.href='/admin/movies'">
                                         <spring:message code="change.movie"/>
                            </button>

                            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large" onclick="location.href='/admin/tickets'">
                                         <spring:message code="all.tickets"/>
                            </button>

                            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large" onclick="location.href='/admin/unactiveschedule'">
                                        <spring:message code="non.active.schedule"/>
                            </button>

                            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large" onclick="location.href='/admin/unactivemovies'">
                                      <spring:message code="non.active.movies"/>
                            </button>
                   </div>
            </c:when>
    </c:choose>
    </div>
    </p>
</div>

<script>

    function addUrlParameter(name, value) {
      var searchParams = new URLSearchParams(window.location.search)
      searchParams.set(name, value)
      window.location.search = searchParams.toString()
    }

    function updateUser(url) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", url);
        xhr.setRequestHeader('Content-Type', 'application/json');
        var body = new Object();
            body["userId"]="${user.userId}";
            body["userLogin"]=document.getElementById("login").value;
            body["userPassword"]=document.getElementById("password").value;
        xhr.send(JSON.stringify(body));
        xhr.onload = function() {
             confirm("User was updated in system");
             window.location.reload();
        };
    }
</script>

</body>
</html>