package com.RoommateCostCalculator.RoommateCostCalculator.Controllers;

import com.RoommateCostCalculator.RoommateCostCalculator.Models.Cost;
import com.RoommateCostCalculator.RoommateCostCalculator.Models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class OwingController {
    @GetMapping("/users/owing/{id}")
    public String getOwing (@PathVariable String id) throws SQLException, ClassNotFoundException {
        //owing load users- for loop each user- new cost-loadcost(userid)- userarraylist.add(cost)
        User user = new User();
        ArrayList<User> userList = new ArrayList<>();
        int userIdInt = Integer.parseInt(id);
        user.getOneUser(userIdInt);
        Cost cost= new Cost();
        cost.loadCosts(user);
        user.getTotalCosts();
        user.getUsers(userList);
        double totalCosts = 0.00;
        for(User i: userList){
            Cost cost1=new Cost();
            cost1.loadCosts(i);
            userList.add(i);
            double costi = i.getTotalCosts();
            totalCosts = totalCosts + costi;
        }
         String finalOwing = null;
        for (int i=0;i<userList.size();i++){
            userList.get(i).owing = (totalCosts/userList.size())-userList.get(i).getTotalCosts();
            finalOwing = userList.get(i).id + " : " + userList.get(i).owing;
        }
        return finalOwing;
    }

}
