<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.demo.Bean.Product, java.util.List" %>
<%
    if (session.getAttribute("user") == null) { response.sendRedirect("login.jsp"); return; }
    List<Product> products = (List<Product>) request.getAttribute("products");
    String msg = (String) request.getAttribute("msg");
    String filterLabel = (String) request.getAttribute("filterLabel");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Products – Cold Storage Management</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<div class="main-content">
    <div class="page-header">
        <h2><%= filterLabel %></h2>
        <a href="AddProductServlet" class="btn btn-primary">+ Add Product</a>
    </div>

    <% if ("added".equals(msg)) { %><div class="alert alert-success">Product added successfully!</div><% } %>
    <% if ("updated".equals(msg)) { %><div class="alert alert-success">Product updated successfully!</div><% } %>
    <% if ("deleted".equals(msg)) { %><div class="alert alert-success">Product deleted successfully!</div><% } %>

    <div class="filter-bar">
        <a href="ViewProductServlet" class="btn btn-sm">All</a>
        <a href="ViewProductServlet?filter=expired" class="btn btn-sm btn-danger">Expired</a>
        <a href="ViewProductServlet?filter=type:Frozen" class="btn btn-sm btn-info">Frozen</a>
        <a href="ViewProductServlet?filter=type:Chilled" class="btn btn-sm btn-info">Chilled</a>
        <a href="ViewProductServlet?filter=cat:Dairy" class="btn btn-sm">Dairy</a>
        <a href="ViewProductServlet?filter=cat:Meat" class="btn btn-sm">Meat</a>
        <a href="ViewProductServlet?filter=cat:Seafood" class="btn btn-sm">Seafood</a>
    </div>

    <div class="table-responsive">
        <table class="data-table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Product Name</th>
                    <th>Category</th>
                    <th>Storage Type</th>
                    <th>Temp (°C)</th>
                    <th>Quantity</th>
                    <th>Expiry Date</th>
                    <th>Location</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <% if (products != null && !products.isEmpty()) {
                for (Product p : products) { %>
                <tr class="<%= "Expired".equals(p.getStatus()) ? "row-danger" : "" %>">
                    <td><%= p.getProductId() %></td>
                    <td><%= p.getProductName() %></td>
                    <td><%= p.getCategory() %></td>
                    <td><%= p.getStorageType() %></td>
                    <td><%= p.getTemperature() %></td>
                    <td><%= p.getQuantity() %> <%= p.getUnit() %></td>
                    <td><%= p.getExpiryDate() %></td>
                    <td><%= p.getLocation() %></td>
                    <td><span class="status-badge status-<%= p.getStatus().toLowerCase() %>"><%= p.getStatus() %></span></td>
                    <td>
                        <a href="UpdateProductServlet?id=<%= p.getProductId() %>" class="btn btn-sm btn-warning">Edit</a>
                        <a href="DeleteProductServlet?id=<%= p.getProductId() %>"
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('Delete this product?')">Delete</a>
                    </td>
                </tr>
            <% } } else { %>
                <tr><td colspan="10" class="text-center">No products found.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
