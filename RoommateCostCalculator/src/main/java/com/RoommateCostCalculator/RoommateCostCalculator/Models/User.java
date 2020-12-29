package com.RoommateCostCalculator.RoommateCostCalculator.Models;
import com.RoommateCostCalculator.RoommateCostCalculator.Models.Cost;

import java.sql.*;
import java.util.ArrayList;


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
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate("insert into users (name) values (\"" + this.name + "\")");
        // TODO: how to get inserted row from an insert statement in JDBC


        con.close();


    }
}
