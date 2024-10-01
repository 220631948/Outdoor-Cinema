package za.ac.cput.domain.user;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Order;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "User ID is required")
    @Size(max = 50)
    @Column(name = "user_first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50)
    @Column(name = "user_last_name")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Size(max = 50)
    @Column(name = "user_email")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 10)
    @NumberFormat(pattern = "123-456-7890")
    @Column(name = "user_phone_number")
    private String phoneNumber;

    @NotBlank(message = "UserName is required")
    @Size(max = 50)
    @Column(name = "user_name")
    private String username;


    @NotBlank(message = "password is required")
    @Size(min = 10, max = 25)
    @Column(name = "user_password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    @Column(name = "user_bookings")
    private Set<Booking> bookings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "user_orders")
    private Set<Order> orders;

    protected User() {
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.username = builder.username;
        this.password = builder.password;
        this.bookings = builder.bookings;
        this.orders = builder.orders;
    }

    public User(@NotBlank(message = "UserName is required") @Size(max = 50) String username, @NotBlank(message = "password is required") @Size(min = 10, max = 25) String password) {
        this.username = username;
        this.password = password;
    }

    // equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName)
                && Objects.equals(email, user.email)
                && Objects.equals(phoneNumber, user.phoneNumber)
                && Objects.equals(username, user.username)
                && Objects.equals(password, user.password)
                && Objects.equals(bookings, user.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, email, phoneNumber, username, password, bookings);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + username + '\'' +
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
        private String username;
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

        public Builder setUserName(String username) {
            this.username = username;
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

        public Builder copy(User user) {
            this.id = user.id;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.email = user.email;
            this.phoneNumber = user.phoneNumber;
            this.username = user.username;
            this.password = user.password;
            this.bookings = user.bookings;
            this.orders = user.orders;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}