package com.demo.Servlet;

import com.demo.Implementation.PasswordResetDAOImpl;
import com.demo.Implementation.UserDAOImpl;
import com.demo.Interface.PasswordResetDAO;
import com.demo.Interface.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {

    private final PasswordResetDAO resetDAO = new PasswordResetDAOImpl();
    private final UserDAO          userDAO  = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String token = req.getParameter("token");
        if (token == null || !resetDAO.isTokenValid(token)) {
            req.setAttribute("error", "Reset link is invalid or has expired.");
            req.getRequestDispatcher("/forgotPassword.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("token", token);
        req.getRequestDispatcher("/resetPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String token    = req.getParameter("token");
        String password = req.getParameter("password");
        String confirm  = req.getParameter("confirmPassword");

        if (!password.equals(confirm)) {
            req.setAttribute("error", "Passwords do not match.");
            req.setAttribute("token", token);
            req.getRequestDispatcher("/resetPassword.jsp").forward(req, resp);
            return;
        }

        String email = resetDAO.getEmailByToken(token);
        if (email == null) {
            req.setAttribute("error", "Reset link is invalid or expired.");
            req.getRequestDispatcher("/forgotPassword.jsp").forward(req, resp);
            return;
        }

        userDAO.updatePassword(email, password);
        resetDAO.deleteToken(token);

        req.setAttribute("success", "Password reset successful. Please login.");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
