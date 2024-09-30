package za.ac.cput.dto;

import lombok.Data;
import za.ac.cput.domain.user.User;
import za.ac.cput.domain.Screening;
import za.ac.cput.domain.Ticket;

import java.util.Date;
import java.util.Set;

@Data
public class BookingDTO {
    private Long id;
    private Screening screening;
    private User user;
    private Set<Ticket> tickets;
    private String bookingReference;
    private Date bookingDate;
}