package com.demo.Implementation;

import com.demo.DBConnection.DBConnection;
import com.demo.Interface.PasswordResetDAO;

import java.sql.*;

public class PasswordResetDAOImpl implements PasswordResetDAO {

    private Connection conn = DBConnection.getConnection();

    @Override
    public boolean saveResetToken(String email, String token) {
        String del = "DELETE FROM password_reset WHERE email=?";
        String ins = "INSERT INTO password_reset (email, token, created_at) VALUES (?, ?, NOW())";
        try (PreparedStatement psDel = conn.prepareStatement(del);
             PreparedStatement psIns = conn.prepareStatement(ins)) {
            psDel.setString(1, email);
            psDel.executeUpdate();
            psIns.setString(1, email);
            psIns.setString(2, token);
            return psIns.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getEmailByToken(String token) {
        String sql = "SELECT email FROM password_reset WHERE token=? " +
                     "AND created_at > DATE_SUB(NOW(), INTERVAL 1 HOUR)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("email");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteToken(String token) {
        String sql = "DELETE FROM password_reset WHERE token=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, token);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isTokenValid(String token) {
        return getEmailByToken(token) != null;
    }
}
