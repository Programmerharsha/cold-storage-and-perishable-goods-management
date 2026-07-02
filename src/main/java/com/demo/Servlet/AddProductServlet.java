package com.demo.Servlet;

import com.demo.Bean.Product;
import com.demo.Implementation.ProductDAOImpl;
import com.demo.Interface.ProductDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Product product = new Product();
            product.setProductName(req.getParameter("productName").trim());
            product.setCategory(req.getParameter("category"));
            product.setStorageType(req.getParameter("storageType"));
            product.setTemperature(Double.parseDouble(req.getParameter("temperature")));
            product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            product.setUnit(req.getParameter("unit"));
            product.setExpiryDate(Date.valueOf(req.getParameter("expiryDate")));
            product.setReceivedDate(Date.valueOf(req.getParameter("receivedDate")));
            product.setSupplierId(req.getParameter("supplierId").trim());
            product.setLocation(req.getParameter("location").trim());
            product.setStatus("Active");
            product.setPrice(Double.parseDouble(req.getParameter("price")));

            boolean success = productDAO.addProduct(product);
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/ViewProductServlet?msg=added");
            } else {
                req.setAttribute("error", "Failed to add product. Please try again.");
                req.getRequestDispatcher("/addProduct.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Invalid input: " + e.getMessage());
            req.getRequestDispatcher("/addProduct.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/addProduct.jsp").forward(req, resp);
    }
}
