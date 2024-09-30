package za.ac.cput.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

import java.util.Objects;
@Getter
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type", nullable = false)
    private TicketType type;


    @Column(name = "ticket_price", nullable = false)
    @NotBlank(message = "price is required")
    @Positive(message = "price must be positive")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double price;

    @Column(name = "ticket_quantity", nullable = false)
    @NotBlank(message = "quantity is required")
    @Positive(message = "quantity must be positive")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @NotBlank(message = "booking is required")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    protected Ticket() {
    }

    private Ticket(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.booking = builder.booking;
        this.seat = builder.seat;
        this.order = builder.order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(price, ticket.price) == 0 && quantity == ticket.quantity && Objects.equals(id, ticket.id) && type == ticket.type && Objects.equals(booking, ticket.booking) && Objects.equals(seat, ticket.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, price, quantity, booking, seat);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", type=" + type +
                ", price=" + price +
                ", quantity=" + quantity +
                ", booking=" + booking +
                ", seat=" + seat +
                '}';
    }

    public static class Builder {
        private Long id;
        private TicketType type;
        private double price;
        private int quantity;
        private Booking booking;
        private Seat seat;
        private Order order;

        public Builder setType(TicketType type) {
            this.type = type;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setBooking(Booking booking) {
            this.booking = booking;
            return this;
        }

        public Builder setSeat(Seat seat) {
            this.seat = seat;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder copy(Ticket ticket) {
            this.id = ticket.id;
            this.type = ticket.type;
            this.price = ticket.price;
            this.quantity = ticket.quantity;
            this.booking = ticket.booking;
            this.seat = ticket.seat;
            this.order = ticket.order;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }
}