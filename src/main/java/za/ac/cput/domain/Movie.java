package za.ac.cput.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@ToString
@Entity
@Table(name = "movies")
public class Movie {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @NotBlank(message = "title is required")
    @Column(name = "movie_title")
    @Size(min = 2, max = 100)
    private String title;

    @NotBlank(message = "genre is required")
    @Column(name = "movie_genre")
    @Enumerated(EnumType.STRING)
    private MovieGenreType genre;

    @NotBlank(message = "director is required")
    @Column(name = "movie_director")
    @Size(min = 5, max = 50)
    private String director;

    @Positive(message = "duration must be positive")
    @Column(name = "movie_duration")
    private int duration;

    @DateTimeFormat(pattern = "yyyy")
    @Column(name = "movie_release_year")
    private java.util.Date releaseYear;

    @Getter
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Screening> screenings;

    protected Movie() {
    }

    public Movie(String title, MovieGenreType genre, String director, int duration, java.util.Date releaseYear, Set<Screening> screenings) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.duration = duration;
        this.releaseYear = releaseYear;
        this.screenings = screenings;
    }

    private Movie(Builder builder) {
        this.title = builder.title;
        this.genre = builder.genre;
        this.director = builder.director;
        this.duration = builder.duration;
        this.releaseYear = builder.releaseYear;
        this.screenings = builder.screenings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Movie movie = (Movie) o;
        return duration == movie.duration
                && Objects.equals(id, movie.id)
                && Objects.equals(title, movie.title)
                && Objects.equals(genre, movie.genre)
                && Objects.equals(director, movie.director)
                && Objects.equals(releaseYear, movie.releaseYear)
                && Objects.equals(createdDate, movie.createdDate)
                && Objects.equals(lastModifiedDate, movie.lastModifiedDate)
                && Objects.equals(screenings, movie.screenings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, director, duration, releaseYear, createdDate, lastModifiedDate, screenings);
    }

    public static class Builder {
        private String title;
        private MovieGenreType genre;
        private String director;
        private int duration;
        private Date releaseYear;
        private Set<Screening> screenings;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setGenre(MovieGenreType genre) {
            this.genre = genre;
            return this;
        }

        public Builder setDirector(String director) {
            this.director = director;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setReleaseYear(Date releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public Builder setScreening(Screening screening) {
            if (this.screenings == null) {
                this.screenings = new HashSet<>();
            }
            this.screenings.add(screening);
            return this;
        }

        public Builder copy(Movie movie) {
            this.title = movie.title;
            this.genre = movie.genre;
            this.director = movie.director;
            this.duration = movie.duration;
            this.releaseYear = movie.releaseYear;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}