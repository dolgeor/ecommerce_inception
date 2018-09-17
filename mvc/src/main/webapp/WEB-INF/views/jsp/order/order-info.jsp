<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #e9e9e9;
        }

        .topnav a {
            float: left;
            display: block;
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #2196F3;
            color: white;
        }

        .topnav .search-container {
            float: right;
        }

        .topnav input[type=text], .topnav input[type=number], .topnav input[type=date] {
            padding: 6px;
            margin-top: 8px;
            font-size: 17px;
            border: none;
        }

        .topnav .search-container button {
            float: right;
            padding: 6px 10px;
            margin-top: 8px;
            margin-right: 16px;
            background: #ddd;
            font-size: 17px;
            border: none;
            cursor: pointer;
        }

        .topnav .search-container button:hover {
            background: #ccc;
        }

        @media screen and (max-width: 600px) {
            .topnav .search-container {
                float: none;
            }

            .topnav a, .topnav input[type=text], .topnav input[type=number], .topnav input[type=date] .topnav .search-container button {
                float: none;
                display: block;
                text-align: left;
                width: 100%;
                margin: 0;
                padding: 14px;
            }

            .topnav input[type=text], .topnav input[type=number], .topnav input[type=date] {
                border: 1px solid #ccc;
            }
        }
    </style>
    <script>
        function validateForm() {
            var quantity = document.orderRegisterForm.quantity.value;
            if (quantity < 1) {
                alert("Quantity can't be less than 1");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="topnav">
    <a class="active" href="/">Home</a>
    <a href="/orders">Orders</a>
</div>

<h2>Order ${order.orderId} </h2>
<h3>Purchased by: ${order.customer.name}, ${order.customer.age}</h3>
<h3>Billing Address: ${order.billingAddress.addressLine}</h3>
<h3>Shipping Address: ${order.shippingAddress.addressLine}</h3>
<h3>Order date: ${order.date}</h3>

<div>
    <c:choose>
        <c:when test="${items.size() == 0 || items == null}">
            <h2>Cart is empty... </h2>
        </c:when>
        <c:otherwise>
            <div>
                <table>
                    <tr>
                        <td>Id</td>
                        <td>Product</td>
                        <td>Sku</td>
                        <td>Price</td>
                        <td>Quantity</td>
                        <td>Total</td>
                        <td></td>
                    </tr>
                    <c:forEach items="${items}" var="item">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.product.name}</td>
                            <td>${item.product.sku}</td>
                            <td>${item.price} $</td>
                            <td>
                                <form:form action="/orders/items/${item.id}/update" method="post" >
                                    <input type="hidden" name="orderId" value="${order.orderId}">
                                    <input min="1"  type="number" value="${item.quantity}"
                                           name="quantity" required ${order.editable ? '':'readonly'}>
                                    <button type="submit"  ${order.editable ? '':'hidden'} >Update</button>
                                </form:form>
                            </td>
                            <td>${item.quantity * item.price} $</td>
                            <td>
                                <form:form action="/orders/items/${item.id}/delete" method="post" >
                                    <input type="hidden" name="orderId" value="${order.orderId}">
                                    <button type="submit"  ${order.editable ? '':'hidden'} >Delete</button>
                                </form:form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>${total} $</td>
                        <td></td>
                    </tr>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<br>
<c:choose>
    <c:when test="${order.editable == true}">
        <div>
            <form action="/orders/find" name="orderRegisterForm" method="post" onsubmit="return validateForm()">
                <div>
                    <b>Add item: </b>

                    <label for="product"><b>Product</b></label>
                    <select name="product" required>
                        <c:forEach var="product" items="${products}">
                            <option value="${product.id}">${product.name}, ${product.price} $</option>
                        </c:forEach>
                    </select>

                    <label for="quantity"><b>Quantity</b></label>
                    <input type="number" min="1" placeholder="Enter quantity" name="quantity" required>

                    <input type="hidden" name="orderId" value="${order.orderId}"/>

                    <button type="submit" class="registerbtn">Add item</button>
                </div>
            </form>
        </div>
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${order.editable and items.size() != 0}">
        <form action="/orders/buy" method="post">
            <div>
                <input type="hidden" name="orderId" value="${order.orderId}"/>
                <button type="submit" class="registerbtn">Buy</button>
            </div>
        </form>
    </c:when>
</c:choose>
</div>
</body>
</html>