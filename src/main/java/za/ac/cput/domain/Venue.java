package za.ac.cput.domain;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venue_id")
    private Long id;

    @NotBlank(message = "name is required")
    @Size(min = 10, max = 50)
    @Column(name = "venue_name")
    private String name;

    @NotBlank(message = "address is required")
    @Size(min = 10, max = 100)
    @Column(name = "venue_address")
    private String address;

    @NotBlank(message = "capacity is required")
    @Column(name = "venue_capacity")
    private int capacity;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "venue_screening")
    private Set<Screening> screenings;

    protected Venue() {
    }

    private Venue(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
        this.capacity = builder.capacity;
        this.screenings = builder.screenings;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<Screening> getScreenings() {
        return screenings;
    }

    // equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Venue venue = (Venue) o;
        return capacity == venue.capacity && Objects.equals(id, venue.id) && Objects.equals(name, venue.name)
                && Objects.equals(address, venue.address) && Objects.equals(screenings, venue.screenings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, capacity, screenings);
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                ", screenings=" + screenings +
                '}';
    }

    public static class Builder {
        private Long id;
        private String name;
        private String address;
        private int capacity;
        private Set<Screening> screenings;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder setScreenings(Set<Screening> screenings) {
            this.screenings = screenings;
            return this;
        }

        public Builder copy(Venue venue) {
            this.id = venue.id;
            this.name = venue.name;
            this.address = venue.address;
            this.capacity = venue.capacity;
            this.screenings = venue.screenings;
            return this;
        }

        public Venue build() {
            return new Venue(this);
        }
    }

}