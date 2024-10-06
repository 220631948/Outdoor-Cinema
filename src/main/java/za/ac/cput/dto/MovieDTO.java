package za.ac.cput.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private String genre;
    private int duration;
}