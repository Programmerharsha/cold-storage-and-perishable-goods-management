<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reset Password – Cold Storage</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="auth-page">
<div class="auth-container">
    <div class="auth-card">
        <h2>🔒 Reset Password</h2>
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="ResetPasswordServlet" method="post">
            <input type="hidden" name="token" value="<%= request.getAttribute("token") %>">
            <div class="form-group">
                <label>New Password</label>
                <input type="password" name="password" required minlength="6" placeholder="Min 6 characters">
            </div>
            <div class="form-group">
                <label>Confirm Password</label>
                <input type="password" name="confirmPassword" required placeholder="Repeat your password">
            </div>
            <button type="submit" class="btn btn-primary btn-full">Reset Password</button>
        </form>
    </div>
</div>
</body>
</html>
