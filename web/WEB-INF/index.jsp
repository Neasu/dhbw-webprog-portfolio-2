<%-- 
    Document   : index
    Created on : 04.11.2017, 15:03:13
    Author     : N3asu
--%>

<%@page import="java.util.List"%>
<%@page import="de.dhbw.comix.database.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

        <!-- FontAwesome -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />

        <link href="https://fonts.googleapis.com/css?family=Fira+Sans" rel="stylesheet"> 

        <script src="assets/js/script.js"></script>
        <link rel="stylesheet" href="assets/css/style.css" />

        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <a class="navbar-brand">Comix-Datenbank</a>
            </div>
        </nav>
        <main>
            <div class="container">
                <div class="col-md-6 container">
                    <c:choose >
                        <c:when test="${empty serien}">
                            <h2>Keine Comics vorhanden!</h2>
                        </c:when>
                        <c:when test="${not empty serien}">
                            <form method="post">
                                <input type="hidden" name="action" value="remove">
                                <c:forEach items="${serien}" var="serie">
                                    <div class="row col-md-12 container panel panel-default serie">
                                        <div class="panel-heading">
                                            <h3>${serie.getSerie()}</h3>
                                        </div>
                                        <div class="panel-body col-md-12 container">
                                            <c:forEach items="${serie.getComics()}" var="comic">
                                                <div class="row">
                                                    <div class="col-md-11">
                                                        <h4>
                                                            <input class="checkbox" type="checkbox" name="removeComic" value="${comic.getId()}">
                                                            ${comic.getTitel()}
                                                        </h4>
                                                        <p>Nummer ${comic.getNummer()}, ${comic.getJahr()}<br/>
                                                            Zeichnungen: ${comic.getZeichner()}, Text: ${comic.getTexter()}</p>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:forEach>
                                <input type="submit" class="btn btn-default" value="Ausgewählte Comics löschen"/> 
                            </form>
                        </c:when>
                    </c:choose>
                </div>
                <div class="col-md-1">

                </div>
                <div class="col-md-5">
                    <div class="row panel panel-default">
                        <form class="panel-body" method="post">
                            <input type="hidden" name="action" value="create">
                            <div class="form-group row">
                                <div class="col-xs-6">
                                    <input class="form-control" name="serie" type="text" placeholder="Serie" value="${serie}"/>
                                </div>
                                <div class="col-xs-3">
                                    <input class="form-control" name="nummer" type="number" placeholder="Nummer" min="1" value="${nummer}"/>
                                </div>
                                <div class="col-xs-3">
                                    <input class="form-control" name="jahr" type="number" placeholder="Jahr" min="1" minlength="4" value="${jahr}"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <input class="form-control" name="titel" type="text" placeholder="Titel" value="${titel}"/>
                            </div>
                            <div class="form-group row">
                                <div class="col-xs-6">
                                    <input class="form-control" name="zeichner" type="text" placeholder="Zeichner" value="${zeichner}"/>
                                </div>
                                <div class="col-xs-6">
                                    <input class="form-control" name="texter" type="text" placeholder="Texter" value="${texter}"/>
                                </div>
                            </div>
                            <div class="row">
                                <input type="submit" class="btn btn-default" value="Comic anlegen"/>
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <c:if test="${not empty fehlermeldungen}">
                            <c:forEach items="${fehlermeldungen}" var="meldung">
                                <div class="alert alert-danger" role="alert">${meldung}</div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </main>
        <footer>

        </footer>
    </body>
</html>
