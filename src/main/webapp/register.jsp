<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register – Cold Storage Management</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="auth-page">
<div class="auth-container">
    <div class="auth-card auth-card-wide">
        <h2>❄ Register Account</h2>
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="RegisterServlet" method="post">
            <div class="form-row">
                <div class="form-group">
                    <label>Full Name *</label>
                    <input type="text" name="fullName" required placeholder="Your full name">
                </div>
                <div class="form-group">
                    <label>Username *</label>
                    <input type="text" name="username" required placeholder="Choose a username">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Email *</label>
                    <input type="email" name="email" required placeholder="your@email.com">
                </div>
                <div class="form-group">
                    <label>Phone</label>
                    <input type="text" name="phone" placeholder="10-digit phone number">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Password *</label>
                    <input type="password" name="password" required minlength="6">
                </div>
                <div class="form-group">
                    <label>Role</label>
                    <select name="role">
                        <option value="Staff">Staff</option>
                        <option value="Manager">Manager</option>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn btn-primary btn-full">Register</button>
        </form>
        <div class="auth-links">
            <a href="login.jsp">Already have an account? Login</a>
        </div>
    </div>
</div>
</body>
</html>
