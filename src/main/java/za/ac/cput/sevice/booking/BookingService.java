package za.ac.cput.sevice.booking;

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

    public Booking getBookingById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Booking not found!"));
    }

    public Booking getBookingByDate(String date) {
        return bookRepository.findByDate(date);
    }

    public Booking addBooking(Booking booking) {
        return  Optional.of(booking).filter(b -> !bookRepository.existsByDate(b.getBookingDate()))
                .map(bookRepository :: save)
                .orElseThrow(() -> new AlreadyExistsException(booking.getDate()+" already exists"));