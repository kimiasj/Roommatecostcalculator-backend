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
    public static class PostCostsRequest{
        public  Double amount;
        public String category;
        public int userId;
        public java.util.Date date = new Date();

    }
}
