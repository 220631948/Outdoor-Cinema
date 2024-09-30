package za.ac.cput.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "order_date", nullable = false)
    @NotBlank(message = "Order date is required")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotBlank(message = "Customer is required")
    private Customer customer;

    @Column(name = "total_amount", nullable = false)
    @Positive(message = "Total amount must be positive")
    private double totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> tickets;

    protected Order() {
    }

    private Order(Builder builder) {
        this.id = builder.id;
        this.orderDate = builder.orderDate;
        this.customer = builder.customer;
        this.totalAmount = builder.totalAmount;
        this.tickets = builder.tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Order order = (Order) o;
        return Double.compare(order.totalAmount, totalAmount) == 0 &&
                Objects.equals(id, order.id) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(tickets, order.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, customer, totalAmount, tickets);
    }

    public static class Builder {
        private Long id;
        private Date orderDate;
        private Customer customer;
        private double totalAmount;
        private Set<Ticket> tickets;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setTickets(Set<Ticket> tickets) {
            this.tickets = tickets;
            return this;
        }

        public Builder copy(Order order) {
            this.id = order.id;
            this.orderDate = order.orderDate;
            this.customer = order.customer;
            this.totalAmount = order.totalAmount;
            this.tickets = order.tickets;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}