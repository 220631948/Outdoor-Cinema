package za.ac.cput.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Booking;

import java.util.Date;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByDate(String date);

    boolean existsByDate(@NotNull(message = "Booking date is required") Date bookingDate);
    // Implement booking repository methods here


}