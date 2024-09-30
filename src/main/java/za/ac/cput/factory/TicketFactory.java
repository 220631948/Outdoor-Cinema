package za.ac.cput.factory;

import za.ac.cput.domain.*;

public class TicketFactory {

    private static void validateTicket(TicketType type, double price, int quantity, Seat seat, Order order) {
        if (type == null) {
            throw new IllegalArgumentException("Ticket type is required");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (seat == null) {
            throw new IllegalArgumentException("Seat is required");
        }
        if (order == null) {
            throw new IllegalArgumentException("Order is required");
        }
    }

    public static Ticket createTicket(TicketType type, double price, int quantity, Booking booking, Seat seat, Order order) {
        validateTicket(type, price, quantity, seat, order);
        if (booking == null) {
            throw new IllegalArgumentException("Booking is required");
        }

        return new Ticket.Builder()
                .setType(type)
                .setPrice(price)
                .setQuantity(quantity)
                .setBooking(booking)
                .setSeat(seat)
                .setOrder(order)
                .build();
    }

    public static Ticket createTicketWithoutBooking(TicketType type, double price, int quantity, Seat seat, Order order) {
        validateTicket(type, price, quantity, seat, order);

        return new Ticket.Builder()
                .setType(type)
                .setPrice(price)
                .setQuantity(quantity)
                .setSeat(seat)
                .setOrder(order)
                .build();
    }

    public static Ticket createTicketWithDefaultType(double price, int quantity, Booking booking, Seat seat, Order order) {
        validateTicket(TicketType.ADULT, price, quantity, seat, order);
        if (booking == null) {
            throw new IllegalArgumentException("Booking is required");
        }

        return new Ticket.Builder()
                .setType(TicketType.ADULT)
                .setPrice(price)
                .setQuantity(quantity)
                .setBooking(booking)
                .setSeat(seat)
                .setOrder(order)
                .build();
    }

    public static Ticket createTicketWithDefaultTypeWithoutBooking(double price, int quantity, Seat seat, Order order) {
        validateTicket(TicketType.ADULT, price, quantity, seat, order);

        return new Ticket.Builder()
                .setType(TicketType.ADULT)
                .setPrice(price)
                .setQuantity(quantity)
                .setSeat(seat)
                .setOrder(order)
                .build();
    }


}