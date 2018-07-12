<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <title>Cooking miam miam : le site de cuisine à manger</title>

    <link rel="stylesheet" href="/lib/bootstrap/dist/css/bootstrap.min.css" />
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
    <div class="row">
        <div class="col-xs-11">
            <h1>Toutes les recettes</h1>
        </div>
        <div class="col-xs-1">
            <a href="/admin/recettes/edit" class="btn btn-default">Ajouter</a>
        </div>
    </div>

    <form class="form-inline" action="/admin/recettes">
        <nav>
            <ul class="pager">
                <li class="previous ${pagination.firstPage ? 'disabled' : ''}"><a href="/admin/recettes?index=${pagination.previousPageIndex}"><span aria-hidden="true">&larr;</span> Précédente</a></li>
                <li><input type="text" class="form-control page" name="index" value="${pagination.pageIndex}" /> / ${pagination.pageCount}</li>
                <li class="next ${pagination.lastPage ? 'disabled' : ''}"><a href="/admin/recettes?index=${pagination.nextPageIndex}">Suivante <span aria-hidden="true">&rarr;</span></a></li>
            </ul>
        </nav>
    </form>

    <table class="table table-striped table-hover table-condensed">
        <c:forEach var="item" items="${items}">
            <tr>
                <td>
                    <a href="/admin/recettes/edit?id=${item.id}">${fn:escapeXml(item.title)}</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form class="form-inline" action="/admin/recettes">
        <nav>
            <ul class="pager">
                <li class="previous ${pagination.firstPage ? 'disabled' : ''}"><a href="/admin/recettes?index=${pagination.previousPageIndex}"><span aria-hidden="true">&larr;</span> Précédente</a></li>
                <li><input type="text" class="form-control page" name="index" value="${pagination.pageIndex}" /> / ${pagination.pageCount}</li>
                <li class="next ${pagination.lastPage ? 'disabled' : ''}"><a href="/admin/recettes?index=${pagination.nextPageIndex}">Suivante <span aria-hidden="true">&rarr;</span></a></li>
            </ul>
        </nav>
    </form>
</div>

<script src="/lib/jquery/dist/jquery.min.js"></script>
<script src="/lib/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>