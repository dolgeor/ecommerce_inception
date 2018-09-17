<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        * {
            box-sizing: border-box;
        }

        /* Add padding to containers */
        .container {
            padding: 16px;
            background-color: white;
        }

        /* Full-width input fields */
        option, select, input[type=text], input[type=number] {
            font-size: large;
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        option, select, input[type=text]:focus, input[type=number]:focus {
            background-color: #ddd;
            outline: none;
        }

        /* Overwrite default styles of hr */
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

        /* Set a style for the submit button */
        .registerbtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        .registerbtn:hover {
            opacity: 1;
        }

        /* Add a blue text color to links */
        a {
            color: dodgerblue;
        }

        /* Set a grey background color and center the text of the "sign in" section */
        .signin {
            background-color: #f1f1f1;
            text-align: center;
        }
    </style>

</head>
<body>
<c:choose>
    <c:when test="${customer == null}">
        <form method="get">
            <div class="container">
                <h1>Register</h1>
                <p>Choose customer to create new order.</p>
                <hr>

                <label for="customer"><b>Customer</b></label>
                <select name="customerId" required>
                    <c:forEach var="customer" items="${customers}">
                        <option value="${customer.id}">${customer.name}, ${customer.age}
                            years
                        </option>
                    </c:forEach>
                </select>
                <br>
                <br>
                <br>

                <button type="submit" class="registerbtn">Next</button>
            </div>
        </form>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${addresses.size() == 0}">
                <h2>Sorry, ${customer.name} doesn't have addresses...</h2>
                <h3><a href="/customers/register/${customer.id}/addresses">add</a></h3>
            </c:when>
            <c:otherwise>
                <form action="/orders/register" method="post">
                    <div class="container">
                        <h1>Register</h1>
                        <p>Choose customer addresses.</p>
                        <hr>

                        <h2>${customer.name}, ${customer.age} years</h2>
                        <br>
                        <br>
                        <br>

                        <label for="billingAddressId"><b>Billing Address</b></label>
                        <select name="billingAddressId" required>
                            <c:forEach var="ba" items="${addresses}">
                                <option value="${ba.id}">${ba}</option>
                            </c:forEach>
                        </select>
                        <br>
                        <br>
                        <br>

                        <label for="shippingAddressId"><b>Shipping Address</b></label>
                        <select name="shippingAddressId" required>
                            <c:forEach var="sa" items="${addresses}">
                                <option value="${sa.id}">${sa}</option>
                            </c:forEach>
                        </select>
                        <br>
                        <br>
                        <br>
                        <hr>
                        <input type="hidden" name="customerId" value="${customer.id}"/>
                        <button type="submit" class="registerbtn">Register</button>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
</body>
</html>
