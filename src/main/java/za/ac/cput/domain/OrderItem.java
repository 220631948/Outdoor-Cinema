package za.ac.cput.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

import java.util.Objects;

@Getter
@ToString
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @NotBlank(message = "Order is required")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "food_item_id", nullable = false)
    @NotBlank(message = "Food item is required")
    private FoodItem foodItem;

    @Column(name = "quantity", nullable = false)
    @Positive(message = "Quantity must be positive")
    @NotBlank(message = "Quantity is required")
    private int quantity;

    @Column(name = "price", nullable = false)
    @Positive(message = "Price must be positive")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    @NotBlank(message = "Price is required")
    private double price;

    protected OrderItem() {
    }

    private OrderItem(Builder builder) {
        this.id = builder.id;
        this.order = builder.order;
        this.foodItem = builder.foodItem;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity &&
                Double.compare(orderItem.price, price) == 0 &&
                Objects.equals(id, orderItem.id) &&
                Objects.equals(order, orderItem.order) &&
                Objects.equals(foodItem, orderItem.foodItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, foodItem, quantity, price);
    }

    public static class Builder {
        private Long id;
        private Order order;
        private FoodItem foodItem;
        private int quantity;
        private double price;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setFoodItem(FoodItem foodItem) {
            this.foodItem = foodItem;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder copy(OrderItem orderItem) {
            this.id = orderItem.id;
            this.order = orderItem.order;
            this.foodItem = orderItem.foodItem;
            this.quantity = orderItem.quantity;
            this.price = orderItem.price;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}