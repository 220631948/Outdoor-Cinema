package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Ticket;
import za.ac.cput.domain.TicketType;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByType(TicketType type);
}