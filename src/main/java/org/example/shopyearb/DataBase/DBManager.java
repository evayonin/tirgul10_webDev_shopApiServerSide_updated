package org.example.shopyearb.DataBase;

import jakarta.annotation.PostConstruct;
import org.example.shopyearb.Entity.Category;
import org.example.shopyearb.Entity.Product;
import org.example.shopyearb.Entity.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBManager {

    private static final String URL = "jdbc:mysql://localhost:3306/shopyearb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    private Connection connection;

    @PostConstruct
    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("DB connected successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
               Category category = new Category(resultSet.getInt("id"),resultSet.getString("name"));
               categories.add(category);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
   }

   public List<Product> getProductsByCategoryId (int categoryId){
      List<Product> products = new ArrayList<>();
      String sql = "SELECT * FROM products WHERE category_id = ?";
      try(PreparedStatement ps  = connection.prepareStatement(sql)){
        ps.setInt(1,categoryId);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next())
        {
            Product product = new Product(resultSet.getInt("id"),
                    resultSet.getInt("price"),resultSet.getString("name"),resultSet.getString("color"));
            products.add(product);
        }
      }catch (SQLException e){
          e.printStackTrace();
      }
      return products;
   }
























    public void insertUser(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                users.add(new User(
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public User getUserByUsername(String username){
        System.out.println(username);
        User user = null;
       try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")){
           preparedStatement.setString(1,username);
           ResultSet  resultSet= preparedStatement.executeQuery();
           if (resultSet.next()){
               user = new User(resultSet.getString("username"),resultSet.getString("password"));
           }
       }catch (SQLException e){

       }
       return user;
    }
}
