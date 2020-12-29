package com.RoommateCostCalculator.RoommateCostCalculator.Controllers;

import com.RoommateCostCalculator.RoommateCostCalculator.Models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class UsersController {


    @GetMapping("/users")
    public ArrayList<User> getUsers() throws SQLException, ClassNotFoundException {
        ArrayList<User> users = new ArrayList<User>();

        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roommatescostcalculator","root","123456");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from users ");
        while(rs.next()){
            User dbuser = new User(rs.getString(2),rs.getInt(1));
            users.add(dbuser);

        }

        con.close();

        return users;
    }

    @PostMapping("/users")
    public User postUsers(@RequestParam String name) throws SQLException, ClassNotFoundException {
        // TODO: how to read values from body

        User newUser = new User(name);
        newUser.save();
        return newUser;

    }

}
