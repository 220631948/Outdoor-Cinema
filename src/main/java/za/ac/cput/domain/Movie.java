package za.ac.cput.domain;

import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long id;

    @NotBlank(message = "title is required")
    @Column(name = "movie_title")
    @Size(min = 2, max = 100)
    private String title;

    @NotBlank(message = "direcot is required")
    @Column(name = "movie_director")
    @Size(min = 5, max = 50)
    private String director;

    // movie duration
    @NotBlank(message = "duration is required")
    @Column(name = "movie_duration")
    private int duration;

}
