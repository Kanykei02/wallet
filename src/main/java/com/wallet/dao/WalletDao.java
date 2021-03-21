package com.wallet.dao;

import com.wallet.model.Wallet;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class WalletDao {
    public String createWallet(Wallet wallet){
        String sql = "insert into wallets(name, user_id, is_active, created_date) values(?,?,?, now())";
        try(Connection conn = new DbConnection().connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, wallet.getName());
            stmt.setInt(2, wallet.getUser().getId());
            stmt.setBoolean(3, wallet.is_active());
            stmt.executeUpdate();
            return "Ok";
        }
        catch (SQLException ex){
            System.out.println(ex.getErrorCode());
        }
        return "Not ok";
    }

    public List<Wallet> getAllWallets(){
        List<Wallet> newWallet = new ArrayList<>();
        String SQL = "select * from wallets";
        try(Connection conn = DbConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)){
            while (rs.next()){
                Wallet wallet = new Wallet(
                        rs.getInt("id"),
                        rs.getString("name"),
                        UserDao.getUserById(rs.getInt("user_id")),
                        rs.getBoolean("is_active"),
                        rs.getDate("created_date")
                );
                newWallet.add(wallet);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return newWallet;
    }

    public static Wallet getWalletById(int id){
        String SQL = "select * from users where id = ?";
        Wallet wallet = null;
        try(Connection conn = DbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next())
                    wallet = new Wallet(
                            rs.getInt("id"),
                            rs.getString("name"),
                            UserDao.getUserById(rs.getInt("id")),
                            rs.getBoolean("is_active"),
                            rs.getDate("created_date")
                    );
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return wallet;
    }

    public String deleteWalletById(int id){
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

    public String updateWallet(Wallet wallet){
        String SQL = "update wallet set name = ?, set user_id = ?, set is_active = ?, set created_date = now() where id = ?";
        try(Connection conn = DbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setString(1, wallet.getName());
            stmt.setInt(2, wallet.getUser().getId());
            stmt.setBoolean(3,  wallet.is_active());
            stmt.setInt(4, wallet.getId());
            stmt.executeUpdate();
            return "ok";
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return "not ok";
    }
}
