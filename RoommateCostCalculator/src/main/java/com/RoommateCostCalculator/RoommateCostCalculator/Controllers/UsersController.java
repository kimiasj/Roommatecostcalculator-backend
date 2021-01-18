package com.RoommateCostCalculator.RoommateCostCalculator.Controllers;

import com.RoommateCostCalculator.RoommateCostCalculator.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;

import static org.apache.tomcat.jni.Mmap.delete;

@RestController
public class UsersController {



    @GetMapping("/users")
    public ArrayList<User> getUsers() throws SQLException, ClassNotFoundException {

        ArrayList<User> users = new ArrayList<User>();
        User newuser = new User();
        newuser.getUsers(users);

        return users;
    }

    @PostMapping("/users")
    public User postUsers(@RequestBody PostUsersRequest postUsersRequest) throws SQLException, ClassNotFoundException {
        User newUser = new User(postUsersRequest.name);
        newUser.save();
        return newUser;
    }

    @DeleteMapping(value = "/users/{id}")
    public String deleteUser(@PathVariable String id) throws ClassNotFoundException, SQLException {
        User newUser = new User();
        newUser.id = Integer.parseInt(id);
        newUser.delete();
        return "delete successful";

    }


}

class PostUsersRequest {
    public String name;

}
