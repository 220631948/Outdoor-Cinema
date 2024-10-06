package za.ac.cput.dto;

import lombok.Data;
import za.ac.cput.domain.user.User;
import za.ac.cput.domain.Ticket;

import java.util.Date;
import java.util.Set;

@Data
public class OrderDTO {
    private Long id;
    private Date orderDate;
    private User user;
    private double totalAmount;
    private Set<Ticket> tickets;
}