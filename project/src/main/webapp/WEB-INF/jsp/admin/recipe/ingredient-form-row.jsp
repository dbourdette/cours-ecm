<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row">
    <div class="col-xs-6">
        <input type="text" class="form-control" placeholder="Nom" name="ingredients[${not empty ingredientIndex ? ingredientIndex : param.ingredientIndex}].name" value="${fn:escapeXml(param.ingredientName)}" />
    </div>
    <div class="col-xs-2">
        <input type="text" class="form-control" placeholder="Quantité" name="ingredients[${not empty ingredientIndex ? ingredientIndex : param.ingredientIndex}].quantity" value="${fn:escapeXml(param.ingredientQuantity)}" />
    </div>
    <div class="col-xs-2">
        <input type="text" class="form-control" placeholder="Unité" name="ingredients[${not empty ingredientIndex ? ingredientIndex : param.ingredientIndex}].unit" value="${fn:escapeXml(param.ingredientUnit)}" />
    </div>
    <div class="col-xs-1">
        <button type="button" class="btn btn-default" data-role="removeIngredient"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button>
    </div>
</div>