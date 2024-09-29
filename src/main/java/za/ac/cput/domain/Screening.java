package za.ac.cput.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "screenings")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screening_id")
    private Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotBlank(message = "name is required")
    @Column(name = "screening_name")
    @Size(min = 10, max = 100, message = "name must be between 10 and 100 characters")
    private String name;

    @NotBlank(message = "time is required")
    @Column(name = "screening_time")
    @Size(min = 10, max = 100, message = "time must be between 10 and 100 characters")
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Booking> bookings;

    protected Screening() {
    }

    private Screening(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.name = builder.name;
        this.time = builder.time;
        this.movie = builder.movie;
        this.venue = builder.venue;
        this.bookings = builder.bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Screening screening = (Screening) o;
        return Objects.equals(id, screening.id) && Objects.equals(date, screening.date)
                && Objects.equals(name, screening.name) && Objects.equals(time, screening.time)
                && Objects.equals(movie, screening.movie) && Objects.equals(venue, screening.venue)
                && Objects.equals(bookings, screening.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, name, time, movie, venue, bookings);
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", movie=" + movie +
                ", venue=" + venue +
                ", bookings=" + bookings +
                '}';
    }

    public static class Builder {
        private Long id;
        private Date date;
        private String name;
        private String time;
        private Movie movie;
        private Venue venue;
        private Set<Booking> bookings;

        public Builder date(String date) {
            this.date = Date.from((Instant) DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(date));
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder time(String time) {
            this.time = time;
            return this;
        }

        public Builder movie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder venue(Venue venue) {
            this.venue = venue;
            return this;
        }

        public Builder bookings(Set<Booking> bookings) {
            this.bookings = bookings;
            return this;
        }

        public Builder copy(Screening screening) {
            this.id = screening.id;
            this.date = screening.date;
            this.name = screening.name;
            this.time = screening.time;
            this.movie = screening.movie;
            this.venue = screening.venue;
            this.bookings = screening.bookings;
            return this;
        }

        public Screening build() {
            return new Screening(this);
        }
    }
}