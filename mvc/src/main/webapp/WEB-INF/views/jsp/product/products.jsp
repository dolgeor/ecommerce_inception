<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
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
</head>
<body>
<div class="topnav">
    <a class="active" href="/">Home</a>
    <a href="/products">Products</a>
    <a href="/products/register">Register</a>

    <div class="search-container">
        <form action="/products/find">
            <input type="text" placeholder="Enter product name ..." name="name" required>
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>
    <div class="search-container">
        <form action="/products/find">
            <input type="text" placeholder="Enter product sku..." name="sku" required>
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>


</div>


<c:choose>
    <c:when test="${products.size() == 0 || products == null}">
        <h2>Sorry, there are no orders... </h2>
    </c:when>
    <c:otherwise>
        <div>
            <h2>Products</h2>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Sku</th>
                    <th>Price</th>
                    <th></th>
                    <th></th>
                    <th></th>

                </tr>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.sku}</td>
                        <td>${product.price}</td>
                        <td>
                            <form:form action="/products/${product.id}" method="get">
                                <button type="submit">Info</button>
                            </form:form>
                        </td>
                        <td>
                            <form:form action="/products/${product.id}/update" method="get">
                                <button type="submit">Update</button>
                            </form:form>
                        </td>
                        <td>
                            <form:form action="/products/${product.id}/delete" method="post">
                                <button type="submit">Delete</button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>