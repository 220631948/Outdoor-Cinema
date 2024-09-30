package za.ac.cput.factory;

import za.ac.cput.domain.user.User;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Ticket;


import java.util.Date;
import java.util.Set;

public class OrderFactory {

    private static void validateOrder(Date orderDate, double totalAmount, User user) {
        if (orderDate == null || orderDate.before(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000))) {
            throw new IllegalArgumentException("Invalid order date");
        }
        if (totalAmount <= 0) {
            throw new IllegalArgumentException("Total amount must be positive");
        }
        if (user == null) {
            throw new IllegalArgumentException("User is required");
        }
    }

    public static Order createOrder(Date orderDate, double totalAmount, User user, Set<Ticket> tickets) {
        validateOrder(orderDate, totalAmount, user);
        if (tickets == null || tickets.isEmpty()) {
            throw new IllegalArgumentException("At least one ticket item is required");
        }

        return new Order.Builder()
                .setOrderDate(orderDate)
                .setTotalAmount(totalAmount)
                .setCustomer(user)
                .setTickets(tickets)
                .build();
    }

    public static Order createOrderWithoutItems(Date orderDate, double totalAmount, User user) {
        validateOrder(orderDate, totalAmount, user);

        return new Order.Builder()
                .setOrderDate(orderDate)
                .setTotalAmount(totalAmount)
                .setCustomer(user)
                .build();
    }
}