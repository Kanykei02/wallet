package com.wallet.dao;

import com.wallet.model.Categories;
import com.wallet.model.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDao {
    public static String createCategory(Categories category){
        String SQL = "insert into categories (name, user_id, parent_category_id, is_active, created_date) values(?,?,?,?, now()) ";
        try(
                Connection conn = DbConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getUser_id().getId());
            stmt.setInt(3, category.getParent_category_id().getId());
            stmt.setBoolean(4, category.getIs_active());
            stmt.executeUpdate();
            return "ok";
        }
        catch (
                SQLException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public List<Categories> getAllCategories(){
        List<Categories> categoriesList = new ArrayList<>();
        String SQL = "select * from categories";
        try(Connection conn = DbConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)){
            while (rs.next()){
                Categories category = new Categories(
                        rs.getInt("id"),
                        rs.getString("name"),
                        UserDao.getUserById(rs.getInt("user_id")),
                        getCategoriesById(rs.getInt("parent_category_id")),
                        rs.getBoolean("is_active"),
                        rs.getDate("created_date")
                );
                categoriesList.add(category);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return categoriesList;
    }

    public static Categories getCategoriesById(int id){
        String SQL = "select * from categories where id = ?";
        Categories category = null;
        try(Connection conn = DbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next())
                    category = new Categories(
                            rs.getInt("id"),
                            rs.getString("name"),
                            UserDao.getUserById(rs.getInt("user_id")),
                            getCategoriesById(rs.getInt("parent_category_id")),
                            rs.getBoolean("is_active"),
                            rs.getDate("created_date")
                    );
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return category;
    }

    public String deleteCatregoryById(int id){
        String SQL = "delete from categories where id = ?";
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

    public String updateCategory(Categories category){
        String SQL = "update Categories set name = ?, set user_id = ?, set parent_category_id  = ?, set is_active  = ?" +
                ", set created_date = now() where id = ?";
        try(Connection conn = DbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getUser_id().getId());
            stmt.setInt(3, category.getParent_category_id() == null ? null : category.getParent_category_id().getId());
            stmt.setBoolean(4,category.getIs_active());
            stmt.setInt(5, category.getId());
            stmt.executeUpdate();
            return "ok";
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "not ok";
    }
}
