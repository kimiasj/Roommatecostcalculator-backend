package com.RoommateCostCalculator.RoommateCostCalculator.Controllers;
import com.RoommateCostCalculator.RoommateCostCalculator.Models.Cost;
import com.RoommateCostCalculator.RoommateCostCalculator.Models.User;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class CostController {
    @PostMapping("/costs")
    public Cost postCosts(@RequestBody PostCostsRequest postCostsRequest) throws SQLException, ClassNotFoundException {
/*
  {
    amount: 20,
    category: "food",
    userId: 5
  }
 */
        User user = new User();
        user.load(postCostsRequest.userId);
      Cost newCost = new Cost(
              postCostsRequest.amount,
              postCostsRequest.category,
              user,
              postCostsRequest.date);
      newCost.saveCost();
      return newCost;

    }
    @GetMapping("/costs")
    public ArrayList<Cost> getCost(@RequestParam String userId)throws ClassNotFoundException,SQLException{
        User user = new User();
        user.id = Integer.parseInt(userId);
        Cost cost = new Cost();
        cost.loadCosts();
        return user.costs;

    }
    @DeleteMapping(value = "/costs/{id}")
    public String deleteCost(@PathVariable String id) throws ClassNotFoundException, SQLException {
        Cost newCost = new Cost();
        newCost.id = Integer.parseInt(id);
        newCost.delete();
        return "delete successful";

    }
    public static class PostCostsRequest{
        public  Double amount;
        public String category;
        public int userId;
        public java.util.Date date = new Date();

    }
}
