package za.ac.cput.dto;

import lombok.Data;
import za.ac.cput.domain.FoodItem;
import za.ac.cput.domain.Order;

@Data
public class OrderItem {
    private Long id;
    private Order order;
    private FoodItem foodItem;
    private int quantity;
    private double price;
}