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
import java.util.List;

@WebServlet("/ViewProductServlet")
public class ViewProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String filter = req.getParameter("filter");
        List<Product> products;

        if ("expired".equals(filter)) {
            products = productDAO.getExpiredProducts();
            req.setAttribute("filterLabel", "Expired / Near-Expiry Products");
        } else if (filter != null && filter.startsWith("cat:")) {
            products = productDAO.getProductsByCategory(filter.substring(4));
            req.setAttribute("filterLabel", "Category: " + filter.substring(4));
        } else if (filter != null && filter.startsWith("type:")) {
            products = productDAO.getProductsByStorageType(filter.substring(5));
            req.setAttribute("filterLabel", "Storage Type: " + filter.substring(5));
        } else {
            products = productDAO.getAllProducts();
            req.setAttribute("filterLabel", "All Products");
        }

        req.setAttribute("products", products);
        req.setAttribute("msg", req.getParameter("msg"));
        req.getRequestDispatcher("/viewProducts.jsp").forward(req, resp);
    }
}
