package za.ac.cput.domain;

import java.util.Objects;
import java.util.Set;

import org.hibernate.mapping.UniqueKey;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    @Size(min = 10, max = 50)
    private String name;

    @NotBlank(message = "address is required")
    @Size(min = 10, max = 100)
    private String address;

    @NotBlank(message = "capacity is required")
    private int capacity;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Screenings> screenings;

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

    public Set<Screenings> getScreenings() {
        return screenings;
    }

    // equals and hashcode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue venue = (Venue) o;
        return capacity == venue.capacity && Objects.equals(id, venue.id) && Objects.equals(name, venue.name) && Objects.equals(address, venue.address) && Objects.equals(screenings, venue.screenings);
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
        private Set<Screenings> screenings;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder screenings(Set<Screenings> screenings) {
            this.screenings = screenings;
            return this;
        }

        public Venue build() {
            return new Venue(this);
        }

        public Builder copy(Venue venue) {
            this.id = venue.id;
            this.name = venue.name;
            this.address = venue.address;
            this.capacity = venue.capacity;
            this.screenings = venue.screenings;
            return this;
        }
    }

}