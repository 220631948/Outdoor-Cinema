package za.ac.cput.dto;

import lombok.Data;

@Data
public class FoodItem {
    private Long id;
    private String name;
    private double price;
    private String description;
    private int quantity;
}