<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
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

        .topnav input[type=text],.topnav input[type=number],.topnav input[type=date] {
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

            .topnav a, .topnav input[type=text],.topnav input[type=number],.topnav input[type=date] .topnav .search-container button {
                float: none;
                display: block;
                text-align: left;
                width: 100%;
                margin: 0;
                padding: 14px;
            }

            .topnav input[type=text],.topnav input[type=number],.topnav input[type=date] {
                border: 1px solid #ccc;
            }
        }
    </style>
</head>
<body>
<div class="topnav">
    <a class="active" href="/">Home</a>
    <a href="/orders">Orders</a>
    <a href="/orders/register">Register</a>

    <div class="search-container">
        <form action="/orders/find" >
                <input type="date" id="dateOfOrder" name="from" required>
                <button type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>
    <div class="search-container">
        <form action="/orders/find">
            <input type="number" placeholder="Enter customer id ..." name="customerId" required>
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>


</div>

<c:choose>
    <c:when test="${orders.size() == 0 || orders == null}">
        <h2>Sorry, there are no orders... </h2>
    </c:when>
    <c:otherwise>
        <div>
            <h2>Orders</h2>


            <table>
                <tr>
                    <th>Id</th>
                    <th>OrderId</th>
                    <th>Customer</th>
                    <th>BillingAddress</th>
                    <th>ShippingAddress</th>
                    <th>Date</th>
                    <th></th>
                </tr>
                <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.orderId}</td>
                    <td>${order.customer}</td>
                    <td>${order.billingAddress}</td>
                    <td>${order.shippingAddress}</td>
                    <td>${order.date}</td>
                    <td>
                        <form:form action="/orders/find" method="get">
                            <input type="hidden" name="orderId" value="${order.orderId}">
                            <button type="submit">Info</button>
                        </form:form>
                    </td>
                    </c:forEach>
            </table>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>