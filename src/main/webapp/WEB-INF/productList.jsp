<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <title>Online Shopping App</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<p>
<c:import url="_header.html" />

<div class="container" role="main">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title"><fmt:message key="label.product_types" /></h3>
        </div>
        <div class="panel-body">
            <c:url var="actionUrl" value="/api/v1/productList"/>
            <form action="${actionUrl}" method="post">
                <util:select label="Product Types" name="productType" options="${productTypes}"></util:select>
                <div class="form-group">
                    <button class="btn btn-success">Filter</button>
                </div>
            </form>
        </div>
    </div>

    <c:if test="${not empty productList}">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title">Available Products</h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-responsive table-hover">
                    <thead>
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${productList}" var="item">
                        <tr>
                            <td>
                                ${item.code} &nbsp;
                                <c:if test="${not empty item.imagePath}">
                                    <img width="25" height="25" src="${item.imagePath}"/>
                                </c:if>
                            </td>
                            <td>${item.name}</td>
                            <td><fmt:formatNumber value="${item.price}" type="currency"/></td>
                            <td>
                                <c:url var="form_cart_list" value="/api/v1/cartList"/>
                                <form method="POST" action="${form_cart_list}">
                                    <input type="hidden" name="productCode" value="${item.code}" />
                                    <button name="addProductToCart" type="addProductToCart" class="btn-link">Buy</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
</div>

<c:import url="_footer.html" />

</body>
</html>