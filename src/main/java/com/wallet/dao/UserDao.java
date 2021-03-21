package com.wallet.dao;

import com.wallet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDao {
    public static String createUser(User user){
        String SQL = "insert into users (name, password, created_date) values(?,?, now()) ";
        try(Connection conn = DbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
            return "ok";
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "not ok";
    }

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        String SQL = "select * from users";
        try(Connection conn = DbConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)){
            while (rs.next()){
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getTimestamp("created_date")
                );
                userList.add(user);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return userList;
    }

    public static User getUserById(int id){
        String SQL = "select * from users where id = ?";
        User user = null;
        try(Connection conn = DbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next())
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("password"),
                            rs.getTimestamp("created_date")
                    );
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return user;
    }

    public String deleteUserById(int id){
        String SQL = "delete from users where id = ?";
        try(Connection conn = DbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "Ok";
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "Not ok";
    }

    public String updateUser(User user){
        String SQL = "update users set name = ?, password = ? where id = ?";
        try(Connection conn = DbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
            return "ok";
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "not ok";
    }
}
