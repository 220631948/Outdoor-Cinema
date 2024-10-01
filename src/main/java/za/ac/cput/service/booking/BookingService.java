package za.ac.cput.service.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.exceptions.AlreadyExistsException;
import za.ac.cput.exceptions.ResourceNotFoundException;
import za.ac.cput.repository.BookingRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookRepository;

    /**
     * Retrieves a booking by its ID.
     *
     * @param id the ID of the booking
     * @return the booking if found
     * @throws ResourceNotFoundException if the booking is not found
     */
    public Booking getBookingById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found!"));
    }

    /**
     * Retrieves a booking by its date.
     *
     * @param date the date of the booking
     * @return the booking if found
     */
    public Booking getBookingByDate(String date) {
        return bookRepository.findByDate(date);
    }

    /**
     * Adds a new booking.
     *
     * @param booking the booking to add
     * @return the saved booking
     * @throws AlreadyExistsException if a booking with the same date already exists
     */
    public Booking addBooking(Booking booking) {
        return Optional.of(booking).filter(b -> !bookRepository.existsByDate(b.getBookingDate()))
                .map(bookRepository::save)
                .orElseThrow(() -> new AlreadyExistsException(booking.getBookingDate() + " already exists"));
    }
}