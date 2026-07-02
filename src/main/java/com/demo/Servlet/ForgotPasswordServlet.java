package com.demo.Servlet;

import com.demo.Implementation.PasswordResetDAOImpl;
import com.demo.Implementation.UserDAOImpl;
import com.demo.Interface.PasswordResetDAO;
import com.demo.Interface.UserDAO;
import com.demo.Util.EmailUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {

    private final UserDAO          userDAO  = new UserDAOImpl();
    private final PasswordResetDAO resetDAO = new PasswordResetDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email").trim();

        if (!userDAO.emailExists(email)) {
            req.setAttribute("error", "No account found with this email address.");
            req.getRequestDispatcher("/forgotPassword.jsp").forward(req, resp);
            return;
        }

        String token = UUID.randomUUID().toString();
        resetDAO.saveResetToken(email, token);

        String resetLink = req.getScheme() + "://" + req.getServerName() + ":"
                + req.getServerPort() + req.getContextPath()
                + "/ResetPasswordServlet?token=" + token;

        EmailUtil.sendPasswordResetEmail(email, resetLink);

        req.setAttribute("success", "Password reset link has been sent to " + email);
        req.getRequestDispatcher("/forgotPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/forgotPassword.jsp").forward(req, resp);
    }
}
