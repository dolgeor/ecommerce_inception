<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    button {
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

    span.step {
      background: #cccccc;
      border-radius: 150px;
      -moz-border-radius: 150px;
      -webkit-border-radius: 150px;
      color: #ffffff;
      display: inline-block;
      font-weight: bold;
      font-size:50px;
      line-height: 250px;
      margin-right: 5px;
      text-align: center;
      width: 250px;
    }

  </style>
</head>
<body>

<h2 style="text-align:center">Product</h2>

<div class="card">
  <span class="step">${product.id}</span>
  <h1>${product.name}</h1>
  <p>${product.sku}</p>
  <p class="title">${product.price} $</p>
  <form method="get" action="/products">
    <button type="submit">Go to Products</button>
  </form>
</div>

</body>
</html>