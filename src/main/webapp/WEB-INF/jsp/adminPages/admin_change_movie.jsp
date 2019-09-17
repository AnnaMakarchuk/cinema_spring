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
    <div class="w3-bar w3-padding-large w3-padding-24">

            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large w3-left" onclick="location.href='/cabinet'">
                            <spring:message code="back.cabinet"/>
            </button>

             <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-teal w3-round-large w3-right-align">
                   <spring:message code="adminadd.movie"/>
                        </button>
                             <div id="id01" class="w3-modal">
                                <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                                    <div class="w3-center"><br>
                                        <span onclick="document.getElementById('id01').style.display='none'"
                                        class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
                                    </div>
                                    <div class="w3-container">
                                        <div class="w3-section w3-left-align">
                                            <label><b><spring:message code="movie.name"/></b></label>
                                            <input class="w3-input w3-border w3-margin-bottom" id = "name" type="text" placeholder="<spring:message code="enter.movie.name"/>" name="movieName" required>

                                            <label><b><spring:message code="duration"/></b></label>
                                            <input class="w3-input w3-border w3-margin-bottom" id = "duration" type="text" placeholder="<spring:message code="enter.movie.duration"/>" name="movieDuration" required>

                                            <label><b><spring:message code="movie.age"/></b></label>
                                            <input class="w3-input w3-border w3-margin-bottom" id = "age" type="text" placeholder="<spring:message code="enter.age"/>" name="ageLimit" required>

                                            <label><b><spring:message code="movie.description"/></b></label>
                                            <input class="w3-input w3-border w3-margin-bottom" id = "description" type="text" placeholder="<spring:message code="enter.description"/>" name="movieDescription" required>

                                               <input type="radio" name="genre" value="comedy"><spring:message code="comedy"/><br>
                                               <input type="radio" name="genre" value="fantasy"><spring:message code="fantasy"/><br>
                                               <input type="radio" name="genre" value="action"><spring:message code="action.genre"/><br>
                                               <input type="radio" name="genre" value="thriller"><spring:message code="thriller"/><br>
                                               <input type="radio" name="genre" value="melodrama"><spring:message code="melodrama"/><br>
                                               <input type="radio" name="genre" value="cartoon"><spring:message code="cartoon"/><br>

                                            <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding" onclick="submitMovie('addmovie')">
                                                <spring:message code="adminadd.movie"/></button>
                                        </div>
                                    </div>
                                </div>
                             </div>

                                <div id="error" class="w3-modal">
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
</div>
<div class="w3-container w3-center">
    <footer class="w3-container w3-teal w3-round-large" style="width:100% ">
        <h4><b><spring:message code="movie.list"/></b></h4>
    </footer>
</div>
<div><p></p></div>
<div class="w3-container w3-text-teal">
        <table class="w3-table w3-small w3-bordered w3-centered">
            <tr>
                <th><spring:message code="movie.name"/></th>
                <th><spring:message code="movie.genre"/></th>
                <th><spring:message code="duration"/></th>
                <th><spring:message code="movie.age"/></th>
                <th><spring:message code="movie.description"/></th>
                <th><spring:message code="action"/></th>
            </tr>
            <tr>
                <c:forEach var="movie" items="${movies}" varStatus = "loopStatus">
                    <td><br><c:out value="${movie.movieName}"/></td>
                    <td><br><c:out value="${movie.movieGenre}"/></td>
                    <td><br><c:out value="${movie.movieDuration}"/></td>
                    <td><br><c:out value="${movie.ageLimit}"/></td>
                    <td><br><c:out value="${movie.movieDescription}"/></td>
                    <td class="w3-left">
                        <form action="/admin/cancelmovie?movie_id=${movie.movieId}" method="post">
                            <button class="w3-button w3-border w3-padding-small" type = "submit">
                                 <spring:message code="cancel.movie"/>
                            </button>
                        </form>

                </tr>
                </c:forEach>
            </tr>

        </table>
</div>

<script>
    function addUrlParameter(name, value) {
      var searchParams = new URLSearchParams(window.location.search)
      searchParams.set(name, value)
      window.location.search = searchParams.toString()
    }

    function submitMovie(url) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", url);
        xhr.setRequestHeader('Content-Type', 'application/json');
        var body = new Object();
                body["movieName"]=document.getElementById("name").value;
                body["movieDuration"]=document.getElementById("duration").value;
                body["ageLimit"]=document.getElementById("age").value;
                body["movieDescription"]=document.getElementById("description").value;
            var radios = document.getElementsByName('genre');
            for (var i = 0, length = radios.length; i < length; i++)
            {
             if (radios[i].checked)
             {
              body["movieGenre"]=radios[i].value;
              break;
             }
            }

        xhr.send(JSON.stringify(body));
        xhr.onload = function() {
             if (xhr.status == 200) {
                 confirm("Movie was added");
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