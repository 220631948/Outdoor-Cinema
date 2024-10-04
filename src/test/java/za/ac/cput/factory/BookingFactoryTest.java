package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingFactoryTest {

    // Initialize the class constructors for the movie, screening, venue, customer, ticket, booking, and promo, respectively

    // Movie
    public static Movie movie = new Movie.Builder().setTitle("The Avengers")
            .setGenre(MovieGenreType.ACTION)
            .setDuration(120)
            .setDirector("Joss Whedon")
            .build();

    // Venue
    public static Venue venue = new Venue.Builder()
            .setName("IMAX")
            .setAddress("Gardens")
            .setCapacity(150)
            .build();

    // Screening
    public static Screening screening;

    static {
        try {
            screening = new Screening.Builder()
                    .movie(movie)
                    .venue(venue)
                    .date(new SimpleDateFormat("yyyy-MM-dd").parse("2024-10-01"))
                    .name("Test Screening")
                    .time("20:00")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // Customer
    public static Customer customer = new Customer.Builder()
            .setFistName("Peter")
            .setLastName("Bird")
            .setEmail("perter.bird@gmail.com")
            .setPhoneNumber("047-123-2555")
            .setPassword("password")
            .build();

    // Seat
    public static Seat seat = new Seat.Builder()
            .setRow("A")
            .setNumber(1)
            .setType(SeatType.STANDARD)
            .setScreening(screening)
            .build();

    // Ticket
    public static Ticket ticket = new Ticket.Builder()
            .setType(TicketType.ADULT)
            .setSeat(seat)
            .setPrice(100.0)
            .build();

    @Test
    void testCreateBookingWithValidParameters() throws ParseException {
        // Arrange
        Screening screening = new Screening.Builder()
                .movie(movie)
                .venue(venue)
                .date(new SimpleDateFormat("yyyy-MM-dd").parse("2024-11-12"))
                .name("Test Screening")
                .time("20:00")
                .build();

        Customer customer = new Customer.Builder()
                .setFistName("John")
                .setLastName("Doe")
                .setEmail("john@example.com")
                .setPhoneNumber("1234567001")
                .setPassword("password")
                .build();

        Set<Ticket> tickets = new HashSet<>();
        tickets.add(new Ticket.Builder().setPrice(100.0).build());

        String bookingReference = "REF123";
        Date bookingDate = new Date();

        // Act
        Booking booking = BookingFactory.createBooking(screening, customer, tickets, bookingReference, bookingDate);

        // Assert
        assertNotNull(booking);
        assertEquals(screening.getMovie(), booking.getScreening().getMovie());
        assertEquals(screening.getVenue(), booking.getScreening().getVenue());
        assertEquals(customer.getFirstName(), booking.getCustomer().getFirstName());
        assertEquals(customer.getLastName(), booking.getCustomer().getLastName());
        assertEquals(tickets, booking.getTickets());
        assertEquals(bookingReference, booking.getBookingReference());
        assertEquals(bookingDate, booking.getBookingDate());
    }

    @Test
    void testCreateBookingWithNullScreening() {
        Customer customer = new Customer.Builder().setFistName("John").setLastName("Doe").build();
        Set<Ticket> tickets = new HashSet<>();
        String bookingReference = "REF123";
        Date bookingDate = new Date();

        Booking result = BookingFactory.createBooking(null, customer, tickets, bookingReference, bookingDate);

        assertNull(result);
    }

    @Test
    void createBookingWithNullCustomer() {
        screening = new Screening.Builder().movie(movie).venue(venue).build();
        Set<Ticket> tickets = new HashSet<>();
        String bookingReference = "REF123";
        Date bookingDate = new Date();

        Booking result = BookingFactory.createBooking(screening, null, tickets, bookingReference, bookingDate);

        assertNull(result);
    }

    @Test
    void createBookingWithNullTickets() {
        screening = new Screening.Builder().movie(movie).venue(venue).build();
        customer = new Customer.Builder().setFistName("John").setLastName("Doe").build();
        String bookingReference = "REF123";
        Date bookingDate = new Date();

        Booking result = BookingFactory.createBooking(screening, customer, null, bookingReference, bookingDate);

        assertNull(result);
    }

    @Test
    void createBookingWithNullBookingReference() {
        screening = new Screening.Builder().movie(movie).venue(venue).build();
        customer = new Customer.Builder().setFistName("John").setLastName("Doe").build();
        Set<Ticket> tickets = new HashSet<>();
        Date bookingDate = new Date();

        Booking result = BookingFactory.createBooking(screening, customer, tickets, null, bookingDate);

        assertNull(result);
    }

    @Test
    void createBookingWithNullBookingDate() {
        screening = new Screening.Builder().movie(movie).venue(venue).build();
        customer = new Customer.Builder().setFistName("John").setLastName("Doe").build();
        Set<Ticket> tickets = new HashSet<>();
        String bookingReference = "REF123";

        Booking result = BookingFactory.createBooking(screening, customer, tickets, bookingReference, null);

        assertNull(result);
    }

    @Test
    void createBookingWithoutBookingReferenceWithValidParameters() {
        Screening screening = new Screening.Builder().movie(movie).venue(venue).build();
        Customer customer = new Customer.Builder().setFistName("John").setLastName("Doe").build();
        Set<Ticket> tickets = new HashSet<>();
        Date bookingDate = new Date();

        Booking booking = BookingFactory.createBookingWithoutBookingReference(screening, customer, tickets, bookingDate);

        assertNotNull(booking);
        assertEquals(screening.getMovie(), booking.getScreening().getMovie());
        assertEquals(screening.getVenue(), booking.getScreening().getVenue());
        assertEquals(customer.getFirstName(), booking.getCustomer().getFirstName());
        assertEquals(customer.getLastName(), booking.getCustomer().getLastName());
        assertEquals(tickets, booking.getTickets());
        assertEquals(bookingDate, booking.getBookingDate());
    }

    @Test
    void createBookingWithoutBookingReferenceWithNullScreening() {
        customer = new Customer.Builder().setFistName("John").setLastName("Doe").build();
        Set<Ticket> tickets = new HashSet<>();
        Date bookingDate = new Date();

        Booking result = BookingFactory.createBookingWithoutBookingReference(screening, customer, tickets, bookingDate);

        assertNull(result);
    }

    @Test
    void createBookingWithoutBookingReferenceWithNullCustomer() {
        Screening screening = new Screening.Builder().movie(movie).venue(venue).build();
        Set<Ticket> tickets = new HashSet<>();
        Date bookingDate = new Date();

        Booking result = BookingFactory.createBookingWithoutBookingReference(screening, customer, tickets, bookingDate);

        assertNull(result);
    }

//    @Test
//    void createBookingWithoutBookingReferenceWithNullTickets() {
//        Screening screening = new Screening.Builder().movie(new Movie()).venue(new Venue()).build();
//        Customer customer = new Customer.Builder().setFistName("John").setLastName("Doe").build();
//        Date bookingDate = new Date();
//
//        Booking result = BookingFactory.createBookingWithoutBookingReference(screening, customer, null, bookingDate);
//
//        assertNull(result);
//    }

//    @Test
//    void createBookingWithoutBookingReferenceWithNullBookingDate() {
//        Screening screening = new Screening.Builder().movie(new Movie()).venue(new Venue()).build();
//        Customer customer = new Customer.Builder().setFistName("John").setLastName("Doe").build();
//        Set<Ticket> tickets = new HashSet<>();
//
//        Booking result = BookingFactory.createBookingWithoutBookingReference(screening, customer, tickets, null);
//
//        assertNull(result);
//    }

}