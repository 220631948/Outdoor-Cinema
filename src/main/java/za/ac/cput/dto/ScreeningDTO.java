package za.ac.cput.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Movie;
import za.ac.cput.domain.Venue;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningDTO {
    private Long id;
    private Date date;
    private String name;
    private String time;
    private Movie movie;
    private Venue venue;
    private Set<Booking> bookings;
}