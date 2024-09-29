package za.ac.cput.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Getter
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    @NotBlank(message = "Screening is required")
    private Screening screening;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotBlank(message = "Customer is required")
    private Customer customer;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> tickets;

    @NotBlank(message = "Booking reference is required")
    @Size(min = 5, max = 20, message = "Booking reference must be between 5 and 20 characters")
    @Column(name = "booking_reference", unique = true)
    private String bookingReference;

    @NotNull(message = "Booking date is required")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "booking_date")
    private Date bookingDate;

    protected Booking() {
    }

    private Booking(Builder builder) {
        this.id = builder.id;
        this.screening = builder.screening;
        this.customer = builder.customer;
        this.tickets = builder.tickets;
        this.bookingReference = builder.bookingReference;
        this.bookingDate = builder.bookingDate;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "screening = " + screening + ", " +
                "customer = " + customer + ", " +
                "bookingReference = " + bookingReference + ", " +
                "bookingDate = " + bookingDate + ")";
    }

    public static class Builder {
        private Long id;
        private Screening screening;
        private Customer customer;
        private Set<Ticket> tickets;
        private String bookingReference;
        private Date bookingDate;

        public Builder setScreening(Screening screening) {
            this.screening = screening;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder setTickets(Set<Ticket> tickets) {
            this.tickets = tickets;
            return this;
        }

        public Builder setBookingReference(String bookingReference) {
            this.bookingReference = bookingReference;
            return this;
        }

        public Builder setBookingDate(Date bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public Builder copy(Booking booking) {
            this.id = booking.id;
            this.screening = booking.screening;
            this.customer = booking.customer;
            this.tickets = booking.tickets;
            this.bookingReference = booking.bookingReference;
            this.bookingDate = booking.bookingDate;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }

    }
}