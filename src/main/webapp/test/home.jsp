<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<style>

    table,tr, th,td {
        padding: 10px;
        border: 2px solid gray;
        border-collapse:collapse
    }
    .csw-btn-button {
        cursor: pointer;
        font-size: 16px;
        padding: 10px 35px;
        color: #ffffff !important;
        border-radius: 5px;
        background: #e79a20;
        border: 1px solid #c98925;
        transition: 0.4s;
    }
    .csw-btn-button:hover {
        background: #292929;
    }
    a{
        color: black;
        text-decoration: none;
    }
</style>
<body>
<center>
    <h1>item Management</h1>
    <h2><a href="/home?action=create" class="csw-btn-button" rel="nofollow">Creat New item</a></h2>

    <br>

    <form action="/home?action=find" method="post">
        <input type="text" name="search" id="search" placeholder="country">
        <input type="submit" value="Search">
    </form>
    <br>
    <h2>
        <a href="/home?action=sort" class="csw-btn-button" rel="nofollow" >Display item List By Name</a>
        <a href="/items" class="csw-btn-button" rel="nofollow" >Display item List</a>
    </h2>

</center>
<div align="center">

    <table >
        <caption><h2>List of items</h2></caption>
        <tr>
            <th>item_id</th>
            <th>item_code</th>
            <th>shop_id</th>
            <th>category_id</th>
            <th>deal_id</th>
            <th>item_name</th>
            <th>item_price</th>
            <th>item_description</th>
            <th>item_image</th>
        </tr>
        <c:forEach var="item" items="${items}">
            <tr>
                <td><c:out value="${item.item_id}"/></td>
                <td>${item.item_code}</td>
                <td>${item.shop_id}</td>
                <td>${item.category_id}</td>
                <td>${item.deal_id}</td>
                <td>${item.item_name}</td>
                <td>${item.item_price}</td>
                <td>${item.item_description}</td>
                <td>${item.item_image}</td>
                <td>
                    <a  href="/home?action=edit&id=${item.item_id}">Edit</a>
                    <a href="/home?action=delete&id=${item.item_id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>