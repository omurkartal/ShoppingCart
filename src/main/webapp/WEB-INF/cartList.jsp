<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
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
    <c:if test="${not empty cartInfoList.cartList}">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title">My Cart [${cartInfoList.uuid}]</h3>
            </div>
            <div class="panel-body">
                <c:url var="form_url" value="/api/v1/cartList/formAction"/>
                <form:form method="POST" action="${form_url}" modelAttribute="cartInfoList">

                <table class="table table-striped table-responsive table-hover">
                    <thead>
                    <tr>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>SubTotal</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cartInfoList.cartList}" var="item" varStatus="vs">
                        <tr>
                            <td>
                                <img width="25" height="25" src="${item.product.imagePath}"/>&nbsp;
                                ${item.product.code} - ${item.product.name}
                            </td>
                            <td><fmt:formatNumber value="${item.product.price}" type="currency"/></td>
                            <td>
                                <form:input path="cartList[${vs.index}].quantity" type="number" id="quantity" name="quantity" min="1" max="1000" value="${item.quantity}"/>
                            </td>
                            <td><fmt:formatNumber value="${item.subTotal}" type="currency"/></td>
                            <td>
                                <a href = "<c:url value="/api/v1/cartList/removeProductFromCart/${item.product.code}"/>">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <table class="table table-striped table-responsive">
                    <colgroup>
                      <col class="col-md-2">
                      <col class="col-md-1">
                      <col class="col-md-1">
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>
                                Discount code: <input type="text" maxlength="5" size="5" id="discountId" name="discountId" value="${cartInfoList.discountId}"/>
                                <button class="btn btn-default" name="addDiscount" type="Add_Discount">Add</button>
                                <button class="btn btn-default" name="removeDiscount" type="RemoveDiscount">Remove</button>
                                &nbsp;&nbsp;Discount ratio: %${cartInfoList.discountRatio}
                            </td>
                            <td>
                                <button class="btn btn-default" name="updateQuantities" type="Update_Quantities">Update Quantities</button>
                            </td>
                            <td>
                                <button class="btn btn-default" name="clearList" type="Clear_List">Clear List</button>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table class="table table-striped table-responsive">
                    <tbody>
                        <tr>
                            <td align="center">
                                <b>Total Amount: <fmt:formatNumber value="${cartInfoList.totalAmount}" type="currency"/></b>
                                &nbsp;&nbsp;
                                <button class="btn btn-success" name="completeOrder" type="Complete_Order">Complete Order</button>
                            </td>
                        </tr>
                    </tbody>
                </table>

                </form:form>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty errorMessage}">
        <div class="panel-heading" align="center">
            <p style="color: red; align: center">${errorMessage}</p>
        </div>
    </c:if>

    <div class="panel panel-success">
        <div class="panel-heading" align="center">
            <a href="/shoppingcart/api/v1/productList">Continue Shopping</a> <br/>
        </div>
    </div>
</div>

<c:import url="_footer.html" />

</body>
</html>