package za.ac.cput.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Entity
@Table(name = "food_items")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodItem_id", nullable = false)
    private Long id;

    @Column(name = "foodItem_name", nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "foodItem_price", nullable = false)
    @NotBlank(message = "Price is required")
    @Positive(message = "Price must be positive")
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,###.00")
    private double price;

    @Column(name = "foodItem_description", nullable = false)
    @NotBlank(message = "Description is required")
    private String description;

    @Column(name = "foodItem_quantity", nullable = false)
    @NotBlank(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Size(min = 1, max = 3, message = "Quantity must be between 1 and 3 characters")
    private int quantity;

    protected FoodItem() {
    }

    private FoodItem(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.quantity = builder.quantity;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    private static class Builder {
        private Long id;
        private String name;
        private double price;
        private String description;
        private int quantity;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder copy(FoodItem foodItem) {
            this.id = foodItem.id;
            this.name = foodItem.name;
            this.price = foodItem.price;
            this.description = foodItem.description;
            this.quantity = foodItem.quantity;
            return this;
        }

        public FoodItem build() {
            return new FoodItem(this);
        }

    }
}