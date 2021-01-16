package com.RoommateCostCalculator.RoommateCostCalculator.Models;
import com.RoommateCostCalculator.RoommateCostCalculator.Models.Cost;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;

import static com.RoommateCostCalculator.RoommateCostCalculator.Models.UserService.newuser;


public class User {
    public String name;
    private ArrayList<Cost> costs;
    Double owing;
    public int id;


    public User(String name, int id) {
        this.id = id;
        this.name = name;
        costs = new ArrayList<Cost>();
    }
    public User (){
    }

    public User(String name) {
        this.name = name;
        costs = new ArrayList<Cost>();
    }


    public void addCost(Cost c) {
        c.user = this;
        costs.add(c);
    }

    public Double getCosts() {
        Double total = 0.0;
        for (int x = 0; x < costs.size(); x++) {
            total = total + costs.get(x).amount;

        }
        return total;
    }

    public void printCosts() {

        for (int i = 0; i < costs.size(); i++) {
            System.out.println(name + " - " + costs.get(i).date + " - " + costs.get(i).category + " - " + costs.get(i).amount); // replace with printCost()
        }
    }

    public void save() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roommatescostcalculator", "root", "123456");
        String sql = "insert into users (name) values (\"" + this.name + "\")";
        PreparedStatement pstmt = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        int rowAffected = pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        int generatedKey =0;
        if(rs.next()){
            generatedKey = rs.getInt(1);
            this.id = generatedKey;
        }con.close();

    }
    public void delete() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roommatescostcalculator", "root", "123456");
        String sql = "Delete from users where id = " + this.id;
        PreparedStatement pstmt = con.prepareStatement(sql);
        int rowAffected = pstmt.executeUpdate();
        con.close();

    }


    public String getId() {
        String id = Integer.toString(this.id);
        return id;

    }

    public void load(int userId) {
        // TODO: read user with userId from db and set the correct values on this object
    }
}
