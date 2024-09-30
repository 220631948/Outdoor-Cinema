package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Ticket;
import za.ac.cput.utils.HelperUtils;

import java.util.Date;
import java.util.List;

public class OrderFactory {

    public static Order createOrder(Date orderDate, double totalAmount, Customer customer, List<Ticket> tickets) {
        if (orderDate == null || orderDate.before(new Date())) {
            throw new IllegalArgumentException("Invalid order date");
        }
        if (totalAmount <= 0) {
            throw new IllegalArgumentException("Total amount must be positive");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer is required");
        }
        if (tickets == null || tickets.isEmpty()) {
            throw new IllegalArgumentException("At least one ticket item is required");
        }

        return new Order.Builder()
                .setOrderDate(orderDate)
                .setTotalAmount(totalAmount)
                .setCustomer(customer).setTickets(tickets)
                .build();
    }

    public static Order createOrderWithoutItems(Date orderDate, double totalAmount, Customer customer) {
        if (orderDate == null || orderDate.before(new Date())) {
            throw new IllegalArgumentException("Invalid order date");
        }
        if (totalAmount <= 0) {
            throw new IllegalArgumentException("Total amount must be positive");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer is required");
        }

        return new Order.Builder()
                .setOrderDate(orderDate)
                .setTotalAmount(totalAmount)
                .setCustomer(customer)
                .build();
    }
}