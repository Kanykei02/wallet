package com.wallet.dao;

import com.wallet.model.Categories;
import com.wallet.model.Orders;
import com.wallet.model.User;
import com.wallet.model.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao {
    public static String createOrder(Orders order){
    String SQL = "insert into orders (category_id, amount, description, wallet_id, is_expense, created_date) values(?,?,?,?,? now()) ";
        try(
    Connection conn = DbConnection.connect();
    PreparedStatement stmt = conn.prepareStatement(SQL)){
        stmt.setInt(1, order.getCategory_id().getId());
        stmt.setInt(2, order.getAmount());
        stmt.setString(3, order.getDescription());
        stmt.setInt(4, order.getWallet_id().getId());
        stmt.setBoolean(5, order.getIs_expense());
        stmt.executeUpdate();
        return "ok";
    }
        catch (
    SQLException e) {
        e.printStackTrace();
    }
        return "not ok";
}

    public List<Orders> getAllOrders(){
        List<Orders> ordersList = new ArrayList<>();
        String SQL = "select * from Orders";
        try(Connection conn = DbConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)){
            while (rs.next()){
                Orders order = new Orders(
                        CategoriesDao.getCategoriesById(rs.getInt("category_id")),
                        rs.getInt("amount"),
                        rs.getString("description"),
                        WalletDao.getWalletById(rs.getInt("wallet_id")),
                        rs.getBoolean("is_expense"),
                        rs.getTimestamp("created_date")
                );
                ordersList.add(order);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return ordersList;
    }

    public static Orders getOrderById(int id){
        String SQL = "select * from users where id = ?";
        Orders order = null;
        try(Connection conn = DbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next())
                    order = new Orders(
                            CategoriesDao.getCategoriesById(rs.getInt("category_id")),
                            rs.getInt("amount"),
                            rs.getString("description"),
                            WalletDao.getWalletById(rs.getInt("wallet_id")),
                            rs.getBoolean("is_expense"),
                            rs.getTimestamp("created_date")
                    );
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return order;
    }

    public String deleteOrderById(int id){
        String SQL = "delete from orders where id = ?";
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

    public String updateOrder(Orders order){
        String SQL = "update orders set category_id = ?, set amount = ?, set description = ?, set wallet_id  = ?, set is_expense  = ?" +
                ", set created_date = now()";
        try(Connection conn = DbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, order.getCategory_id().getId());
            stmt.setInt(2, order.getAmount());
            stmt.setString(3, order.getDescription());
            stmt.setInt(4, order.getWallet_id().getId());
            stmt.setBoolean(5, order.getIs_expense());
            stmt.executeUpdate();
            return "ok";
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "not ok";
    }
}
