package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Ticket;
import za.ac.cput.utils.HelperUtils;

import java.util.Date;
import java.util.List;

public class OrderFactory {

    private static void validateOrder(Date orderDate, double totalAmount, Customer customer) {
        if (orderDate == null || orderDate.before(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000))) {
            throw new IllegalArgumentException("Invalid order date");
        }
        if (totalAmount <= 0) {
            throw new IllegalArgumentException("Total amount must be positive");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer is required");
        }
    }

    public static Order createOrder(Date orderDate, double totalAmount, Customer customer, List<Ticket> tickets) {
        validateOrder(orderDate, totalAmount, customer);
        if (tickets == null || tickets.isEmpty()) {
            throw new IllegalArgumentException("At least one ticket item is required");
        }

        return new Order.Builder()
                .setOrderDate(orderDate)
                .setTotalAmount(totalAmount)
                .setCustomer(customer)
                .setTickets(tickets)
                .build();
    }

    public static Order createOrderWithoutItems(Date orderDate, double totalAmount, Customer customer) {
        validateOrder(orderDate, totalAmount, customer);

        return new Order.Builder()
                .setOrderDate(orderDate)
                .setTotalAmount(totalAmount)
                .setCustomer(customer)
                .build();
    }
}