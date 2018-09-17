<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            max-width: 300px;
            margin: auto;
            text-align: center;
            font-family: arial;
        }

        .title {
            color: grey;
            font-size: 18px;
        }

        #goToCustomers {
            border: none;
            outline: 0;
            display: inline-block;
            padding: 8px;
            color: white;
            background-color: #000;
            text-align: center;
            cursor: pointer;
            width: 100%;
            font-size: 18px;
        }

        a {
            text-decoration: none;
            font-size: 22px;
            color: black;
        }

        button:hover, a:hover {
            opacity: 0.7;
        }
        input {
            font-size: large;
        }
        span.step {
            background: #cccccc;
            border-radius: 150px;
            -moz-border-radius: 150px;
            -webkit-border-radius: 150px;
            color: #ffffff;
            display: inline-block;
            font-weight: bold;
            font-size: 50px;
            line-height: 250px;
            margin-right: 5px;
            text-align: center;
            width: 250px;
        }

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            font-size: large;
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

<h2 style="text-align:center">Customer</h2>

<div class="card">
    <span class="step">${customer.id}</span>
    <h1>${customer.name}</h1>
    <p>${customer.age} years</p>
    <form method="get" action="/customers">
        <button type="submit" id="goToCustomers">Go to Customers</button>
    </form>
</div>

<div>
    <c:choose>
        <c:when test="${!(adresses.size() == 0 || adresses == null)}">
            <div>
                <table>
                    <tr>
                        <td>Id</td>
                        <td>Address</td>
                        <td></td>
                    </tr>
                    <c:forEach items="${adresses}" var="item">
                        <tr>
                            <td>${item.id}</td>
                            <td>
                                <form:form action="/customers/${customer.id}/addresses/${item.id}/update" method="post">
                                    <input type="text" value="${item.addressLine}"  size="50" name="addressLine" required>
                                    <button type="submit">Update</button>
                                </form:form>
                            </td>
                            <td>
                                <form:form action="/customers/${customer.id}/addresses/${item.id}/delete" method="post">
                                    <button type="submit">Delete</button>
                                </form:form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:when>
    </c:choose>
</div>
</body>
</html>
