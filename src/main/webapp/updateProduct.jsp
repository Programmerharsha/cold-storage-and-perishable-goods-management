<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.demo.Bean.Product" %>
<%
    if (session.getAttribute("user") == null) { response.sendRedirect("login.jsp"); return; }
    Product p = (Product) request.getAttribute("product");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Product – Cold Storage</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<div class="main-content">
    <h2>Update Product</h2>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
    <% } %>
    <div class="form-card">
        <form action="UpdateProductServlet" method="post">
            <input type="hidden" name="productId" value="<%= p.getProductId() %>">
            <div class="form-row">
                <div class="form-group">
                    <label>Product Name</label>
                    <input type="text" name="productName" value="<%= p.getProductName() %>" required>
                </div>
                <div class="form-group">
                    <label>Category</label>
                    <select name="category" required>
                        <% String[] cats = {"Dairy","Meat","Seafood","Vegetables","Fruits","Beverages","Bakery","Other"};
                           for (String c : cats) { %>
                            <option value="<%= c %>" <%= c.equals(p.getCategory()) ? "selected" : "" %>><%= c %></option>
                        <% } %>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Storage Type</label>
                    <select name="storageType" required>
                        <option value="Frozen" <%= "Frozen".equals(p.getStorageType()) ? "selected" : "" %>>Frozen</option>
                        <option value="Chilled" <%= "Chilled".equals(p.getStorageType()) ? "selected" : "" %>>Chilled</option>
                        <option value="Ambient" <%= "Ambient".equals(p.getStorageType()) ? "selected" : "" %>>Ambient</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Temperature (°C)</label>
                    <input type="number" name="temperature" step="0.1" value="<%= p.getTemperature() %>" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Quantity</label>
                    <input type="number" name="quantity" value="<%= p.getQuantity() %>" required>
                </div>
                <div class="form-group">
                    <label>Unit</label>
                    <select name="unit">
                        <% String[] units = {"kg","litre","piece","box","crate"};
                           for (String u : units) { %>
                            <option value="<%= u %>" <%= u.equals(p.getUnit()) ? "selected" : "" %>><%= u %></option>
                        <% } %>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Expiry Date</label>
                    <input type="date" name="expiryDate" value="<%= p.getExpiryDate() %>" required>
                </div>
                <div class="form-group">
                    <label>Status</label>
                    <select name="status">
                        <option value="Active" <%= "Active".equals(p.getStatus()) ? "selected" : "" %>>Active</option>
                        <option value="Expired" <%= "Expired".equals(p.getStatus()) ? "selected" : "" %>>Expired</option>
                        <option value="Disposed" <%= "Disposed".equals(p.getStatus()) ? "selected" : "" %>>Disposed</option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Supplier ID</label>
                    <input type="text" name="supplierId" value="<%= p.getSupplierId() %>">
                </div>
                <div class="form-group">
                    <label>Location</label>
                    <input type="text" name="location" value="<%= p.getLocation() %>" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Price (₹)</label>
                    <input type="number" name="price" step="0.01" value="<%= p.getPrice() %>" required>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Update Product</button>
                <a href="ViewProductServlet" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
