package za.ac.cput.dto;

import lombok.Data;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Seat;
import za.ac.cput.domain.TicketType;

@Data
public class TicketDTO {
    private Long id;
    private TicketType type;
    private double price;
    private int quantity;
    private Booking booking;
    private Seat seat;
    private Order order;
}