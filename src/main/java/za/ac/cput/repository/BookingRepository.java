package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByDate(String date);
    // Implement booking repository methods here

}