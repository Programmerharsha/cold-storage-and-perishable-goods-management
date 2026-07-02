<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  if (session.getAttribute("user") == null) { response.sendRedirect("login.jsp"); return; } %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Product – Cold Storage Management</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<div class="main-content">
    <h2>Add New Product</h2>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
    <% } %>
    <div class="form-card">
        <form action="AddProductServlet" method="post">
            <div class="form-row">
                <div class="form-group">
                    <label>Product Name *</label>
                    <input type="text" name="productName" required placeholder="e.g., Fresh Salmon">
                </div>
                <div class="form-group">
                    <label>Category *</label>
                    <select name="category" required>
                        <option value="">-- Select Category --</option>
                        <option value="Dairy">Dairy</option>
                        <option value="Meat">Meat</option>
                        <option value="Seafood">Seafood</option>
                        <option value="Vegetables">Vegetables</option>
                        <option value="Fruits">Fruits</option>
                        <option value="Beverages">Beverages</option>
                        <option value="Bakery">Bakery</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Storage Type *</label>
                    <select name="storageType" required>
                        <option value="Frozen">Frozen (below -18°C)</option>
                        <option value="Chilled">Chilled (0°C – 5°C)</option>
                        <option value="Ambient">Ambient (15°C – 25°C)</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Storage Temperature (°C) *</label>
                    <input type="number" name="temperature" step="0.1" required placeholder="e.g., -18">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Quantity *</label>
                    <input type="number" name="quantity" min="0" required>
                </div>
                <div class="form-group">
                    <label>Unit *</label>
                    <select name="unit" required>
                        <option value="kg">kg</option>
                        <option value="litre">litre</option>
                        <option value="piece">piece</option>
                        <option value="box">box</option>
                        <option value="crate">crate</option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Received Date *</label>
                    <input type="date" name="receivedDate" required>
                </div>
                <div class="form-group">
                    <label>Expiry Date *</label>
                    <input type="date" name="expiryDate" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Supplier ID</label>
                    <input type="text" name="supplierId" placeholder="e.g., SUP001">
                </div>
                <div class="form-group">
                    <label>Storage Location *</label>
                    <input type="text" name="location" required placeholder="e.g., Zone A, Rack 3">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Price per Unit (₹) *</label>
                    <input type="number" name="price" step="0.01" min="0" required>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Add Product</button>
                <a href="ViewProductServlet" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
