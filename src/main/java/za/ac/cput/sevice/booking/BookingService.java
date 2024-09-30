package za.ac.cput.sevice.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.exceptions.ResourceNotFoundException;
import za.ac.cput.repository.BookingRepository;

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
    
}