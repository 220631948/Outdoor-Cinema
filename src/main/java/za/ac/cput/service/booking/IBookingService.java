package za.ac.cput.service.booking;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Venue;
import za.ac.cput.service.IService;

@Service
public interface IBookingService extends IService<Booking, Long> {
    Booking getByBookingId(Long bookingId);

    Booking getBookingsByUserId(Long userId);

    Booking getBookingsByVenue(Venue venue);

    void cancelBooking(Long bookingId);

    Booking updateBooking(Booking booking);

}