package com.demo.Implementation;

import com.demo.Bean.Product;
import com.demo.DBConnection.DBConnection;
import com.demo.Interface.ProductDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private Connection conn = DBConnection.getConnection();

    private Product mapRow(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setProductId(rs.getInt("product_id"));
        p.setProductName(rs.getString("product_name"));
        p.setCategory(rs.getString("category"));
        p.setStorageType(rs.getString("storage_type"));
        p.setTemperature(rs.getDouble("temperature"));
        p.setQuantity(rs.getInt("quantity"));
        p.setUnit(rs.getString("unit"));
        p.setExpiryDate(rs.getDate("expiry_date"));
        p.setReceivedDate(rs.getDate("received_date"));
        p.setSupplierId(rs.getString("supplier_id"));
        p.setLocation(rs.getString("location"));
        p.setStatus(rs.getString("status"));
        p.setPrice(rs.getDouble("price"));
        return p;
    }

    @Override
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products (product_name, category, storage_type, temperature, " +
                     "quantity, unit, expiry_date, received_date, supplier_id, location, status, price) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getCategory());
            ps.setString(3, product.getStorageType());
            ps.setDouble(4, product.getTemperature());
            ps.setInt(5, product.getQuantity());
            ps.setString(6, product.getUnit());
            ps.setDate(7, product.getExpiryDate());
            ps.setDate(8, product.getReceivedDate());
            ps.setString(9, product.getSupplierId());
            ps.setString(10, product.getLocation());
            ps.setString(11, product.getStatus());
            ps.setDouble(12, product.getPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET product_name=?, category=?, storage_type=?, temperature=?, " +
                     "quantity=?, unit=?, expiry_date=?, supplier_id=?, location=?, status=?, price=? " +
                     "WHERE product_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getCategory());
            ps.setString(3, product.getStorageType());
            ps.setDouble(4, product.getTemperature());
            ps.setInt(5, product.getQuantity());
            ps.setString(6, product.getUnit());
            ps.setDate(7, product.getExpiryDate());
            ps.setString(8, product.getSupplierId());
            ps.setString(9, product.getLocation());
            ps.setString(10, product.getStatus());
            ps.setDouble(11, product.getPrice());
            ps.setInt(12, product.getProductId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY received_date DESC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> getExpiredProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE expiry_date < CURDATE() OR status='Expired'";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> getProductsByStorageType(String storageType) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE storage_type=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, storageType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateProductQuantity(int productId, int quantity) {
        String sql = "UPDATE products SET quantity=? WHERE product_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
