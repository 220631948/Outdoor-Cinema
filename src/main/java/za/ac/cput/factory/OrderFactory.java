package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.Ticket;
import za.ac.cput.domain.user.User;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderFactory {

    /**
     * Creates an Order with the specified details.
     *
     * @param orderDate the date of the order
     * @param totalAmount the total amount of the order
     * @param user the user placing the order
     * @param tickets the set of tickets in the order
     * @return the created Order
     * @throws IllegalArgumentException if any argument is invalid
     */
    public static Order createOrder(Date orderDate, double totalAmount, User user, Set<Ticket> tickets) {
        validateOrderDetails(orderDate, totalAmount, user);
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

    /**
     * Creates an Order without any ticket items.
     *
     * @param orderDate the date of the order
     * @param totalAmount the total amount of the order
     * @param user the user placing the order
     * @return the created Order
     * @throws IllegalArgumentException if any argument is invalid
     */
    public static Order createOrderWithoutItems(Date orderDate, double totalAmount, User user) {
        validateOrderDetails(orderDate, totalAmount, user);

        return new Order.Builder()
                .setOrderDate(orderDate)
                .setTotalAmount(totalAmount)
                .setCustomer(user)
                .build();
    }

    /**
     * Validates the common order details.
     *
     * @param orderDate the date of the order
     * @param totalAmount the total amount of the order
     * @param user the user placing the order
     * @throws IllegalArgumentException if any argument is invalid
     */
    private static void validateOrderDetails(Date orderDate, double totalAmount, User user) {
        if (orderDate == null || orderDate.before(new Date())) {
            throw new IllegalArgumentException("Invalid order date");
        }
        if (totalAmount <= 0) {
            throw new IllegalArgumentException("Total amount must be positive");
        }
        if (user == null) {
            throw new IllegalArgumentException("Customer is required");
        }
    }
}