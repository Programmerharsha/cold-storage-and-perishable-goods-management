<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.demo.Bean.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) { response.sendRedirect("login.jsp"); return; }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard – Cold Storage Management</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<div class="main-content">
    <h2>Welcome, <%= user.getFullName() %> <span class="badge"><%= user.getRole() %></span></h2>
    <div class="dashboard-grid">
        <a href="ViewProductServlet" class="dash-card">
            <div class="dash-label">All Products</div>
        </a>
        <a href="AddProductServlet" class="dash-card">
            <div class="dash-label">Add Product</div>
        </a>
        <a href="ViewProductServlet?filter=expired" class="dash-card dash-alert">
            <div class="dash-label">Expired Products</div>
        </a>
        <a href="ViewProductServlet?filter=type:Frozen" class="dash-card">
            <div class="dash-label">Frozen Storage</div>
        </a>
        <a href="ViewProductServlet?filter=type:Chilled" class="dash-card">
            <div class="dash-label">Chilled Storage</div>
        </a>
        
    </div>
</div>
</body>
</html>
