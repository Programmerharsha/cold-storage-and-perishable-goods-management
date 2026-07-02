package com.demo.Servlet;

import com.demo.Bean.User;
import com.demo.Implementation.UserDAOImpl;
import com.demo.Interface.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");
        String email    = req.getParameter("email").trim();
        String fullName = req.getParameter("fullName").trim();
        String phone    = req.getParameter("phone").trim();
        String role     = req.getParameter("role");

        if (userDAO.emailExists(email)) {
            req.setAttribute("error", "Email already registered. Please use a different email.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFullName(fullName);
        user.setPhone(phone);
        user.setRole(role != null ? role : "Staff");

        boolean success = userDAO.registerUser(user);
        if (success) {
            req.setAttribute("success", "Registration successful! Please login.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Registration failed. Please try again.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/register.jsp");
    }
}
