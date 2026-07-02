package com.demo.Interface;

public interface PasswordResetDAO {
    boolean saveResetToken(String email, String token);
    String getEmailByToken(String token);
    boolean deleteToken(String token);
    boolean isTokenValid(String token);
}
