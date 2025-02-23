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

    @NotBlank(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private SeatType type;

    @NotBlank(message = "Row is required")
    @Column(name = "seat_row", nullable = false)
    private String row;

    @Column(name = "seat_number", nullable = false)
    @NotBlank(message = "Seat number is required")
    private int number;

    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;

    protected Seat() {
    }

    private Seat(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.row = builder.row;
        this.number = builder.number;
        this.screening = builder.screening;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return number == seat.number && Objects.equals(id, seat.id) && type == seat.type && Objects.equals(row, seat.row) && Objects.equals(screening, seat.screening);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, row, number, screening);
    }

    public static class Builder {
        private Long id;
        private SeatType type;
        private String row;
        private int number;
        private Screening screening;

        public Builder setType(SeatType type) {
            this.type = type;
            return this;
        }

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
            this.type = seat.type;
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