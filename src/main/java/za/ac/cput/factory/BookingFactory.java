package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Screening;
import za.ac.cput.domain.Ticket;
import za.ac.cput.utils.HelperUtils;

import java.util.Date;
import java.util.Set;

public class BookingFactory {

    public static Booking createBooking(Screening screening, Customer customer, Set<Ticket> tickets,
                                        String bookingReference, Date bookingDate) {

        if (HelperUtils.isNullOrEmpty(screening)
                || HelperUtils.isNullOrEmpty(customer)
                || HelperUtils.isNullOrEmpty(tickets)
                || HelperUtils.isNullOrEmpty(bookingReference)
                || HelperUtils.isNullOrEmpty(bookingDate)) {
            return null;
        }

        Screening screening1 = new Screening.Builder()
                .movie(screening.getMovie())
                .venue(screening.getVenue())
                .date(screening.getDate())
                .name(screening.getName())
                .time(screening.getTime())
                .bookings(screening.getBookings())
                .build();

        Customer customer1 = new Customer.Builder()
                .setFistName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setPhoneNumber(customer.getPhoneNumber())
                .setPassword(customer.getPassword())
                .setBookings(customer.getBookings())
                .setOrders(customer.getOrders())
                .build();

        return new Booking.Builder()
                .setScreening(screening1)
                .setCustomer(customer1)
                .setTickets(tickets)
                .setBookingReference(bookingReference)
                .setBookingDate(bookingDate)
                .build();
    }

    // build a booking without BookingReference
    public static Booking createBookingWithoutBookingReference(Screening screening, Customer customer, Set<Ticket> tickets,
                                        Date bookingDate) {

        if (HelperUtils.isNullOrEmpty(screening)
                || HelperUtils.isNullOrEmpty(customer)
                || HelperUtils.isNullOrEmpty(tickets)
                || HelperUtils.isNullOrEmpty(bookingDate)) {
            return null;
        }

        Screening screening1 = new Screening.Builder()
                .movie(screening.getMovie())
                .venue(screening.getVenue())
                .date(screening.getDate())
                .name(screening.getName())
                .time(screening.getTime())
                .bookings(screening.getBookings())
                .build();

        Customer customer1 = new Customer.Builder()
                .setFistName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setPhoneNumber(customer.getPhoneNumber())
                .setPassword(customer.getPassword())
                .setBookings(customer.getBookings())
                .setOrders(customer.getOrders())
                .build();

        return new Booking.Builder()
                .setScreening(screening1)
                .setCustomer(customer1)
                .setTickets(tickets)
                .setBookingDate(bookingDate)
                .build();
    }

}