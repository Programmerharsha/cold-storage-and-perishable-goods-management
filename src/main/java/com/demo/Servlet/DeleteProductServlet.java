package com.demo.Servlet;

import com.demo.Implementation.ProductDAOImpl;
import com.demo.Interface.ProductDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            int productId = Integer.parseInt(idParam);
            boolean success = productDAO.deleteProduct(productId);
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/ViewProductServlet?msg=deleted");
            } else {
                resp.sendRedirect(req.getContextPath() + "/ViewProductServlet?msg=deleteFailed");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/ViewProductServlet");
        }
    }
}
