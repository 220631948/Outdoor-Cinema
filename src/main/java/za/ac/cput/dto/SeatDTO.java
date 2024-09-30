package za.ac.cput.dto;

import lombok.Data;
import za.ac.cput.domain.Screening;
import za.ac.cput.domain.SeatType;

@Data
public class SeatDTO {
    private Long id;
    private SeatType type;
    private String row;
    private int number;
    private Screening screening;

}