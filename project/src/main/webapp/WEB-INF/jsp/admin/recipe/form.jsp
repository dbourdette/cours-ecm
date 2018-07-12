<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <title>Cooking miam miam : le site de cuisine à manger</title>

    <link rel="stylesheet" href="/lib/jquery-ui/themes/smoothness/jquery-ui.min.css" />
    <link rel="stylesheet" href="/lib/bootstrap/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/lib/bootstrap-tokenfield/dist/css/bootstrap-tokenfield.min.css" />
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Cooking Miam Miam</a>
        </div>

        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/admin/recettes">Recettes</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h1>Edition de recette</h1>

    <c:if test="${not empty flashMessage}">
        <div class="alert alert-success" role="alert">${fn:escapeXml(flashMessage)}</div>
    </c:if>

    <form:form action="/admin/recettes/edit" method="post">
        <form:hidden path="id"/>
        <form:hidden path="imageId"/>
        <div class="row">
            <div class="col-xs-12 col-sm-3">
                <div class="thumbnail">
                    <img src="/image/${command.imageId}" alt="${fn:escapeXml(command.title)}">
                    <div class="caption"></div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-9">
                <c:if test="${not empty command.id}">
                    <div class="row">
                        <div class="col-xs-12">
                            <a href="/recette/${command.id}" class="pull-right">Voir sur le site</a>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-xs-12 col-sm-8">
                        <spring:bind path="title">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="title">Titre ${fn:escapeXml(status.errorMessage)}</label>
                                <form:input cssClass="form-control" id="title" placeholder="Titre" path="title" />
                            </div>
                        </spring:bind>
                    </div>
                    <div class="col-xs-12 col-sm-4">
                        <spring:bind path="date">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="date">Date ${fn:escapeXml(status.errorMessage)}</label>
                                <form:input type="date" cssClass="form-control" id="date" placeholder="Date" path="date" />
                            </div>
                        </spring:bind>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <spring:bind path="intro">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="intro">Intro ${fn:escapeXml(status.errorMessage)}</label>
                                <form:input cssClass="form-control" id="intro" placeholder="Intro" path="intro" />
                            </div>
                        </spring:bind>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <spring:bind path="tags">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="tags">Tags ${fn:escapeXml(status.errorMessage)}</label>
                                <form:input cssClass="form-control" id="tags" data-role="tags" placeholder="tag1,tag2" path="tagsAsString" />
                            </div>
                        </spring:bind>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <spring:bind path="text">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="text">Texte ${fn:escapeXml(status.errorMessage)}</label>
                                <form:textarea cssClass="form-control" id="text" placeholder="Texte" path="text" rows="10" />
                            </div>
                        </spring:bind>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <spring:bind path="ingredients">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label">Ingredients ${fn:escapeXml(status.errorMessage)}</label>
                                <button type="button" class="btn btn-default btn-xs" data-role="addIngredient"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
                                <div id="ingredients">
                                    <c:forEach var="ingredient" varStatus="loopStatus" items="${command.ingredients}">
                                        <jsp:include page="ingredient-form-row.jsp">
                                            <jsp:param name="ingredientIndex" value="${loopStatus.index}" />
                                            <jsp:param name="ingredientName" value="${ingredient.name}" />
                                            <jsp:param name="ingredientQuantity" value="${ingredient.quantity}" />
                                            <jsp:param name="ingredientUnit" value="${ingredient.unit}" />
                                        </jsp:include>
                                    </c:forEach>
                                </div>
                            </div>
                        </spring:bind>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <button type="submit" class="btn btn-primary">Sauver</button>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
</div>

<script src="/lib/jquery/dist/jquery.min.js"></script>
<script src="/lib/jquery-ui/jquery-ui.min.js"></script>
<script src="/lib/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="/lib/bootstrap-tokenfield/dist/bootstrap-tokenfield.min.js"></script>
<script src="/js/script.js"></script>
</body>
</html>