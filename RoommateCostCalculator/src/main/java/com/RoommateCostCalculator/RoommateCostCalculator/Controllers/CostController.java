package com.RoommateCostCalculator.RoommateCostCalculator.Controllers;
import com.RoommateCostCalculator.RoommateCostCalculator.Models.Cost;
import com.RoommateCostCalculator.RoommateCostCalculator.Models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.Date;

@RestController
public class CostController {
    @PostMapping("/costs")
    public Cost postCosts(@RequestBody PostCostsRequest postCostsRequest) throws SQLException, ClassNotFoundException {

      Cost newCost = new Cost(postCostsRequest.amount,postCostsRequest.category,postCostsRequest.user,postCostsRequest.date);
      newCost.saveCost();
      return newCost;

    }
    public static class PostCostsRequest{
        public  Double amount;
        public String category;
        public User user;
        public int id;
        public java.util.Date date = new Date();

    }
}
