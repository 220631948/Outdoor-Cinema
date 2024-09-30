package za.ac.cput.factory;

import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Order;
import za.ac.cput.utils.HelperUtils;

public class OrderItemFactory {

    public static OrderItem createOrderItem(Order order, int quantity, double price) {
        return new OrderItem.Builder()
                .setOrder(order)
                .setQuantity(quantity)
                .setPrice(price)
                .build();
    }

    public static OrderItem createOrderItemWithoutPrice(Order order, int quantity) {
        return new OrderItem.Builder()
                .setOrder(order)
                .setQuantity(quantity)
                .build();
    }

    public static OrderItem createOrderItemWithoutQuantity(Order order, double price) {
        if (order == null) {
            throw new IllegalArgumentException("Order is required");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }

        return createOrderItem(order, 1, price);
    }
}