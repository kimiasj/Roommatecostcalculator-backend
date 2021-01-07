package com.RoommateCostCalculator.RoommateCostCalculator.Models;

import java.util.Date;

public class Cost {

    Double amount;
    Category category;
    User user;
    int id;
    Date date = new Date();
    Cost(double newamount){
        amount = newamount;

    }
    Cost(int id, Double amount, Category category, User user,Date date){
       this.amount = amount;
       this.category = category;
       this.user = user;
       this.date = date;
       this.id = id;

    }

}
enum Category{
    FOOD, RENT, GAS, POWER, INTERNET
}
