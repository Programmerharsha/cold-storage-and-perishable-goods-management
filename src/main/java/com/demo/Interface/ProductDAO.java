package com.demo.Interface;

import com.demo.Bean.Product;
import java.util.List;

public interface ProductDAO {
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int productId);
    Product getProductById(int productId);
    List<Product> getAllProducts();
    List<Product> getExpiredProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByStorageType(String storageType);
    boolean updateProductQuantity(int productId, int quantity);
}
