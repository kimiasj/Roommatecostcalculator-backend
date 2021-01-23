package com.RoommateCostCalculator.RoommateCostCalculator.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Cost {

    public Double amount;
    public String category;
    @JsonIgnore
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
        String sql = "insert into costs (cost, category, createdAt, user_id) values (" + this.amount + " ,\"" + this.category + "\" ,\"" + this.convertTime() + "\", " + this.user.id + ")";
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

    public ArrayList<Cost> loadCosts(User user) throws SQLException , ClassNotFoundException{
        this.id = user.id;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roommatescostcalculator","root","123456");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from costs where user_id = " + id);
        ArrayList<Cost> costs = new ArrayList<>();
        while(rs.next()){
                    Cost dbcost = new Cost(rs.getInt(1),rs.getDouble(2),String.valueOf(rs.getString(3)),this.user,rs.getDate(4));
                    costs.add(dbcost);
        }
        return costs;
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
    public String convertTime(){
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String nowAsISO = df.format(this.date);
        return nowAsISO;
    }



}


