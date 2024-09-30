package za.ac.cput.dto;

import lombok.Data;
import za.ac.cput.domain.Screening;

import java.util.Set;

@Data
public class VenueDTO {
    private Long id;
    private String name;
    private String address;
    private int capacity;
    private Set<Screening> screenings;
}