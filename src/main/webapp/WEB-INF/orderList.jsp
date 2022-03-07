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
    <c:if test="${not empty orderList}">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title"><fmt:message key="label.my_orders" /></h3>
            </div>
            <div class="panel-body">

            <c:forEach items="${orderList}" var="orderItem" varStatus="vs">
                <b> Order Id: ${orderItem.orderId} - Total Amount: <fmt:formatNumber value="${orderItem.totalAmount}" type="currency"/></b>
                <c:if test="${not empty orderItem.discountId}">
                    &nbsp;&nbsp;&nbsp;(Discount Id/Ratio: ${orderItem.discountId} / %${orderItem.discountRatio})
                </c:if>

                <table class="table table-striped table-responsive table-hover">
                    <thead>
                    <tr>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>SubTotal</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderItem.cartList}" var="item" varStatus="vs">
                        <tr>
                            <td>
                                <img width="25" height="25" src="${item.product.imagePath}"/>&nbsp;
                                ${item.product.code} - ${item.product.name}
                            </td>
                            <td><fmt:formatNumber value="${item.product.price}" type="currency"/></td>
                            <td>${item.quantity}</td>
                            <td><fmt:formatNumber value="${item.subTotal}" type="currency"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            <hr>
            </c:forEach>

            </div>
        </div>
    </c:if>
</div>

<c:import url="_footer.html" />

</body>
</html>