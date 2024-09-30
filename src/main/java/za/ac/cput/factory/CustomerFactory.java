package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Order;
import za.ac.cput.utils.HelperUtils;

import java.util.Set;

public class CustomerFactory {

    public static Customer createCustomer(String firstName, String lastName, String email) {
        if (!HelperUtils.isNullOrEmpty(firstName)
                || !HelperUtils.isNullOrEmpty(lastName)
                || !HelperUtils.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid name or email");
        }

        return new Customer.Builder()
                .setFistName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .build();
    }

    // factory with firstname lastname, email, phone number and password
    public static Customer createCustomer(String firstName, String lastName, String email, String phoneNumber,
            String password) {
        if (!HelperUtils.isNullOrEmpty(firstName)
                || !HelperUtils.isNullOrEmpty(lastName)
                || !HelperUtils.isValidEmail(email)
                || !HelperUtils.isNullOrEmpty(phoneNumber)
                || !HelperUtils.isNullOrEmpty(password)) {
            throw new IllegalArgumentException("Invalid name, email, phone number or password");
        }

        return new Customer.Builder()
                .setFistName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setPassword(password)
                .build();
    }

    public static Customer createCustomerWithOrders(String firstName, String lastName, String email,
            java.util.Set<Order> orders) {
        if (!HelperUtils.isNullOrEmpty(firstName)
                || !HelperUtils.isNullOrEmpty(lastName)) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (!HelperUtils.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }

        return new Customer.Builder()
                .setFistName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setOrders(orders)
                .build();
    }

    // create customer with bookings
    public static Customer createCustomerWithBookings(String firstName, String lastName, String email,
            java.util.Set<Order> orders, Set<Booking> bookings) {
        if (!HelperUtils.isNullOrEmpty(firstName)
                || !HelperUtils.isNullOrEmpty(lastName)) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (!HelperUtils.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        return new Customer.Builder()
                .setFistName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setOrders(orders)
                .setBookings(bookings)
                .build();
    }

}