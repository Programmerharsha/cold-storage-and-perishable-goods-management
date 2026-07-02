<%@ page import="com.demo.Bean.User" %>
<%  User navUser = (User) session.getAttribute("user"); %>
<nav class="navbar">
    <div class="nav-brand">Cold Storage</div>
    <div class="nav-links">
        <a href="dashboard.jsp">Dashboard</a>
        <a href="ViewProductServlet">Products</a>
        <a href="ViewProductServlet?filter=expired">Expiry Alerts</a>
    </div>
    <div class="nav-user">
        <span><%= navUser != null ? navUser.getUsername() : "" %></span>
        <a href="LogoutServlet" class="btn btn-sm btn-danger">Logout</a>
    </div>
</nav>
