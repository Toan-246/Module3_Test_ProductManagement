<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product List</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>
<div class="container">
    <a class="btn btn-info" href="/products?action=create">Add New product</a>
    <form action="/products" class="float-end">
        <input type="text" name="q" placeholder="Search">
        <button class="btn btn-primary">Search</button>
    </form>
    <h2>Product List</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Product Name</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Color</th>
            <th scope="col">Description</th>
            <th colspan="2" scope="col">Action</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.color}</td>
                <td>${product.description}</td>

                <td>
                    <a class="btn btn-info" href="/products?action=edit&id=${product.id}">
                        <i class="fas fa-edit"></i>
                    </a>
                </td>
                <td><a class="btn btn-danger" href="/products?action=delete&id=${product.id}"><i
                        class="fas fa-trash"></i></a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
