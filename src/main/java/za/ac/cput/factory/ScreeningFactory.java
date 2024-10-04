package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Movie;
import za.ac.cput.domain.Screening;
import za.ac.cput.domain.Venue;

import java.util.Date;
import java.util.Set;

public class ScreeningFactory {

    public static Screening createScreening(Movie movie, Venue venue, Date date, String name, String time, Set<Booking> bookings) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie is required");
        }
        if (venue == null) {
            throw new IllegalArgumentException("Venue is required");
        }
        if (date == null || date.before(new Date())) {
            throw new IllegalArgumentException("Invalid screening date");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Screening name is required");
        }
        if (time == null || time.isEmpty()) {
            throw new IllegalArgumentException("Screening time is required");
        }

        return new Screening.Builder()
                .movie(movie)
                .venue(venue)
                .date(date)
                .name(name)
                .time(time)
                .bookings(bookings)
                .build();
    }

    public static Screening createScreeningWithoutBookings(Movie movie, Venue venue, Date date, String name, String time) {
        return createScreening(movie, venue, date, name, time, null);
    }

    public static Screening createScreeningWithoutTime(Movie movie, Venue venue, Date date, String name, Set<Booking> bookings) {
        return createScreening(movie, venue, date, name, "00:00", bookings);
    }
}