package com.RoommateCostCalculator.RoommateCostCalculator.Models;

import java.sql.*;
import java.util.Date;

public class Cost {

    Double amount;
    String category;
    User user;
    int id;
    Date date = new Date();
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
    public void saveCost()throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roommatescostcalculator","root","123456");
        String sql = "insert into costs (costs,category, createdAt, user_id) values ("  +this.amount+" ,\"" + this.category+ "\" ,\"" +this.date.toString()+ "\", " +this.user.id+")";
        // TODO: fix the sql statement
        // insert into costs (cost, category, createdAt, user_id) values (100, "FOOD", "2021-01-13 00:00:00", 1)
        PreparedStatement pstmt = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        int rs=pstmt.executeUpdate();
        ResultSet r = pstmt.getGeneratedKeys();
        int generatedKey =0;
        if(r.next()){
            generatedKey = r.getInt(1);
            this.id = generatedKey;
        }con.close();
    }


}


