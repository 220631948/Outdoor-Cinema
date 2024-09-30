package za.ac.cput.domain;

import java.util.Objects;
import java.util.Set;

import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer ID is required")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50)
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Size(max = 50)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 10)
    @NumberFormat(pattern = "123-456-7890")
    private String phoneNumber;

    @NotBlank(message = "password is required")
    @Size(min = 10, max = 25)
    private String password;

    @OneToMany(mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Booking> bookings;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

    protected Customer() {
    }

    private Customer(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.password = builder.password;
        this.bookings = builder.bookings;
        this.orders = builder.orders;
    }

    // equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(firstName, customer.firstName)
                && Objects.equals(email, customer.email) && Objects.equals(phoneNumber, customer.phoneNumber)
                && Objects.equals(password, customer.password) && Objects.equals(bookings, customer.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, email, phoneNumber, password, bookings);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", bookings=" + bookings +
                ", orders=" + orders +
                '}';
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String password;
        private Set<Booking> bookings;
        private Set<Order> orders;

        public Builder setFistName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setBookings(Set<Booking> bookings) {
            this.bookings = bookings;
            return this;
        }

        public Builder setOrders(Set<Order> orders) {
            this.orders = orders;
            return this;
        }

        public Builder copy(Customer customer) {
            this.id = customer.id;
            this.firstName = customer.firstName;
            this.lastName = customer.lastName;
            this.email = customer.email;
            this.phoneNumber = customer.phoneNumber;
            this.password = customer.password;
            this.bookings = customer.bookings;
            this.orders = customer.orders;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}