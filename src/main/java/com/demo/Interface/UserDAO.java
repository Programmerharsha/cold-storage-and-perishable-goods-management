package com.demo.Interface;

import com.demo.Bean.User;
import java.util.List;

public interface UserDAO {
    boolean registerUser(User user);
    User loginUser(String username, String password);
    boolean updateUser(User user);
    boolean deleteUser(int userId);
    User getUserById(int userId);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    boolean updatePassword(String email, String newPassword);
    boolean emailExists(String email);
}
