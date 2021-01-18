package com.RoommateCostCalculator.RoommateCostCalculator.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Cost {

    public Double amount;
    public String category;
    public User user;
    public int id;
    public Date date = new Date();
    public Cost(){

    }
    public Cost(double newamount){
        amount = newamount;

    }
    public Cost( Double amount, String category, User user,Date date){
        this.amount = amount;
        this.category = category;
        this.user = user;
        this.date = date;

    }
    Cost(int id, Double amount, String category, User user,Date date){
       this.amount = amount;
       this.category = category;
       this.user = user;
       this.date = date;
       this.id = id;

    }
    public void saveCost()throws ClassNotFoundException,SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roommatescostcalculator", "root", "123456");
        String sql = "insert into costs (cost, category, createdAt, user_id) values (" + this.amount + " ,\"" + this.category + "\" ,\"" + this.date.toString() + "\", " + this.user.id + ")";
        // TODO: fix the sql statement
        // insert into costs (cost, category, createdAt, user_id) values (100, "FOOD", "2021-01-13 00:00:00", 1)
        PreparedStatement pstmt = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);

        int rs = pstmt.executeUpdate();
        ResultSet r = pstmt.getGeneratedKeys();
        int generatedKey = 0;
        if (r.next()) {
            generatedKey = r.getInt(1);
            this.id = generatedKey;
        }
        con.close();

    }

    public void loadCosts() throws SQLException , ClassNotFoundException{

        ArrayList<User> users = new ArrayList<>();
        User newuser = new User();
        newuser.getUsers(users);
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roommatescostcalculator","root","123456");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from costs ");
        while(rs.next()){
            for (User i:users
            ) {
                if (i.id == rs.getInt(5)){
                    Cost dbcost = new Cost(rs.getInt(1),rs.getDouble(2),String.valueOf(rs.getString(3)),i,rs.getDate(4));
                    i.addCost(dbcost);
                }
            }
        }
    }

    public void delete() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roommatescostcalculator", "root", "123456");
        String sql = "Delete from costs where id = " + this.id;
        PreparedStatement pstmt = con.prepareStatement(sql);
        int rowAffected = pstmt.executeUpdate();
        con.close();

    }



}


