package demoAppProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.sql.*;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        String url = "jdbc:h2:file:./data/testdb";
        String user = "sa";
        String password = "";
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to H2 database established!");

            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS product (productId BIGINT PRIMARY KEY AUTO_INCREMENT, productName VARCHAR(255), description VARCHAR(255), quantity INT, price FLOAT)";
            System.out.println("Creating table if not exists...");
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully.");

        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Borro la base una vez finalizado el programa.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            File dbFile = new File("./data/testdb.mv.db");
            if (dbFile.exists()) {
                dbFile.deleteOnExit();
                System.out.println("The database has been deleted.");
            }
        }));
    }
}