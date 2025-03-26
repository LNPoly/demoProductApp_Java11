package demoAppProduct.controller;

import demoAppProduct.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final String url = "jdbc:h2:file:./data/testdb";
    private final String user = "sa";
    private final String password = "";

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String sqlInsert = "INSERT INTO product (productName, description, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sqlInsert);

                pstmt.setString(1, product.getProductName());
                pstmt.setString(2, product.getDescription());
                pstmt.setInt(3, product.getQuantity());
                pstmt.setFloat(4, product.getPrice());
                pstmt.executeUpdate();

            return ResponseEntity.ok( Map.of("status","success",
                    "message","The product has been successfully created.",
                    "data", product));

        }catch (SQLException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @GetMapping("/productsList")
    public ResponseEntity<Map<String, Object>> getProducts(){
        List<Product> productList;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sqlSelect = "SELECT * FROM product order by price";
            productList = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(sqlSelect);
            ResultSet rset = ps.executeQuery();
            while (rset.next()) {
                Product product = new Product(rset.getLong("productId"), rset.getString("productName"),
                        rset.getString("description"),
                        rset.getInt("quantity"),
                        rset.getFloat("price"));
                productList.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(Map.of("status", "success",
                "message", "The filter has been applied successfully.",
                "data", productList));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sqlSelect = "SELECT * FROM product p WHERE p.productId = ?";
            PreparedStatement ps = connection.prepareStatement(sqlSelect);
            ps.setLong(1, id);
            ResultSet rset = ps.executeQuery();
            Product product;
            if (rset.next()) {
                product = new Product(rset.getLong("productId"), rset.getString("productName"),
                        rset.getString("description"),
                        rset.getInt("quantity"),
                        rset.getFloat("price"));
            } else {
                throw new RuntimeException("The product with number ID: " + id + " not found.");
            }

            return ResponseEntity.ok(Map.of("status", "success",
                    "message", "The filter has been applied successfully.",
                    "data", product));
        }
    }

    @GetMapping("/productByName/{name}")
    public ResponseEntity<?> getByNameProduct(@PathVariable String name) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sqlName = "SELECT * FROM product p WHERE p.productName = ?";
            PreparedStatement ps = connection.prepareStatement(sqlName);
            ps.setString(1, name);
            ResultSet rset = ps.executeQuery();
            Product product;

            if (rset.next()) {
                product = new Product(rset.getLong("productId"), rset.getString("productName"),
                    rset.getString("description"),
                    rset.getInt("quantity"),
                    rset.getFloat("price"));

            } else {
                throw new RuntimeException("Product with NAME " + name + " not found.");
            }
            return ResponseEntity.ok(Map.of("status", "success",
                    "message", "The filter has been applied successfully.",
                    "data", product));
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sqlUpdate = "UPDATE product SET productId = ?, productName = ?, description = ?, quantity = ?, price = ? WHERE productId = ?";
            String sqlSelect = "SELECT productId, productName, description, quantity, price FROM product WHERE productId = ?";
            PreparedStatement psUpdate = connection.prepareStatement(sqlUpdate);
            PreparedStatement psSelect = connection.prepareStatement(sqlSelect);

            //actualizando el producto
            psUpdate.setLong(1, id);
            psUpdate.setString(2, product.getProductName());
            psUpdate.setString(3, product.getDescription());
            psUpdate.setInt(4, product.getQuantity());
            psUpdate.setFloat(5, product.getPrice());
            psUpdate.setLong(6, id);

            int rowsAffected = psUpdate.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("The product with number ID: " + id + " not found.");
            }
            psSelect.setLong(1, id);
            try (ResultSet rset = psSelect.executeQuery()) {
                if (rset.next()) {
                    new Product(rset.getLong("productId"),
                        rset.getString("productName"),
                        rset.getString("description"),
                        rset.getInt("quantity"),
                        rset.getFloat("price")
                    );
                } else {
                    throw new RuntimeException("The product with number ID: " + id + " not found.");
                }
            }
            return ResponseEntity.ok(Map.of("status", "success",
                    "message", "The product has been successfully updated.",
                    "data",product));
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sqlDelete = "DELETE FROM product WHERE productId = ?";
            PreparedStatement ps = connection.prepareStatement(sqlDelete);
            ps.setLong(1, id);
            int rowToDelete = ps.executeUpdate();

            if(rowToDelete == 0){
                throw new RuntimeException("The product with number ID: " + id + " not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(Map.of("status", "success",
                "message", "The product has been deleted."));
    }
}