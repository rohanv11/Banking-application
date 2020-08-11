package com.bank.model;

import com.bank.main.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConfig {

    //Step 1: Info

    String userDB = "root";
    String passwordDB = "";
    String url = "jdbc:mysql://localhost:3306/bank2";
    String driver = "com.mysql.jdbc.Driver";
    private Connection con;

    //Step 2: Load Driver and make the connection

    private void dbConnect() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        con = DriverManager.getConnection(url, userDB, passwordDB);
    }

    private void dbClose() throws SQLException {
        con.close();
    }

    public void createAccount(Customer c) throws ClassNotFoundException, SQLException {
        dbConnect();
        String sql = "insert into customer(name,address,email,accountNumber,balance)"
                + "values (?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, c.getName());
        pstmt.setString(2, c.getAddress());
        pstmt.setString(3, c.getEmail());
        pstmt.setString(4, c.getAccountNumber());
        pstmt.setString(5, c.getBalance());
        pstmt.executeUpdate();
        dbClose();
    }
    public int selBal(int accNum) throws ClassNotFoundException, SQLException {
        dbConnect();
        
        String sql = "select balance from customer where accountNumber=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        pstmt.setInt(1, accNum);
        ResultSet rs =pstmt.executeQuery();
        rs.next();
        int bal= rs.getInt(1);
        return bal;
        
        
    }
    public void depo(int accNum,int amount) throws ClassNotFoundException, SQLException {
        dbConnect();
        int bal=selBal(accNum);
        String sql = "UPDATE customer\n" +
"set balance=?\n" +
"WHERE accountNumber=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, amount+bal);
        pstmt.setInt(2, accNum);
        pstmt.executeUpdate();
        dbClose();
        
    }

    public void withdraw(int accNum, int amount) throws ClassNotFoundException, SQLException {
        dbConnect();
        int bal=selBal(accNum);
        String sql = "UPDATE customer\n" +
"set balance=?\n" +
"WHERE accountNumber=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,bal-amount);
        pstmt.setInt(2, accNum);
        pstmt.executeUpdate();
        dbClose();
    }
}
