package com.RoommateCostCalculator.RoommateCostCalculator.Controllers;

import com.RoommateCostCalculator.RoommateCostCalculator.Models.Cost;
import com.RoommateCostCalculator.RoommateCostCalculator.Models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

@RestController
public class OwingController {
    @GetMapping("/users/owing")
    public ArrayList<Owing> getOwing () throws SQLException, ClassNotFoundException{
        //owing load users- for loop each user- new cost-loadcost(userid)- userarraylist.add(cost)
        User user = new User();
        ArrayList<User> userList = new ArrayList<>();
        user.getUsers(userList);
        double totalCosts = 0.00;
        for(User i: userList){
            Cost cost1=new Cost();
            cost1.loadCosts(i);
            double costi = i.getTotalCosts();
            totalCosts = totalCosts + costi;
        }
         String finalOwing = "";
        ArrayList<Owing> owings = new ArrayList<Owing>();
        for (int i=0;i<userList.size();i++){
            userList.get(i).owing = (totalCosts/userList.size())-userList.get(i).getTotalCosts();
            Owing owing = new Owing(userList.get(i).id , userList.get(i).owing);
            owings.add(owing);
        }
        return owings;
    }

}

class Owing {
    public int id;
    public double owing;
    public Owing(int id, double owing){
        this.id = id;
        this.owing = owing;
    }
}
