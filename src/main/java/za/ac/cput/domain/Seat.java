package za.ac.cput.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Row is required")
    @Column(name = "seat_row", nullable = false)
    private String row;

    @Column(name = "seat_number", nullable = false)
    @NotBlank(message = "Seat number is required")
    private int number;

    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;

    protected Seat() {}

    private Seat(Builder builder) {
        this.id = builder.id;
        this.row = builder.row;
        this.number = builder.number;
        this.screening = builder.screening;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return number == seat.number && Objects.equals(id, seat.id) && Objects.equals(row, seat.row) && Objects.equals(screening, seat.screening);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, number, screening);
    }

    public static class Builder {
        private Long id;
        private String row;
        private int number;
        private Screening screening;

        public Builder setRow(String row) {
            this.row = row;
            return this;
        }

        public Builder setNumber(int number) {
            this.number = number;
            return this;
        }

        public Builder setScreening(Screening screening) {
            this.screening = screening;
            return this;
        }

        public Builder copy(Seat seat) {
            this.id = seat.id;
            this.row = seat.row;
            this.number = seat.number;
            this.screening = seat.screening;
            return this;
        }

        public Seat build() {
            return new Seat(this);
        }
    }
}