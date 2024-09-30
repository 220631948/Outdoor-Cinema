package za.ac.cput.factory;

import za.ac.cput.domain.Screening;
import za.ac.cput.domain.Venue;

import java.util.Set;

public class VenueFactory {

    public static Venue createVenue(String name, String address, int capacity, Set<Screening> screenings) {
        validateVenueFields(name, address, capacity, screenings);

        return new Venue.Builder()
                .setName(name)
                .setAddress(address)
                .setCapacity(capacity)
                .setScreenings(screenings)
                .build();
    }

    public static Venue createVenueWithoutScreenings(String name, String address, int capacity) {
        return createVenue(name, address, capacity, null);
    }

    public static Venue createVenueWithDefaultCapacity(String name, String address, Set<Screening> screenings) {
        int defaultCapacity = 100; // Default capacity value
        return createVenue(name, address, defaultCapacity, screenings);
    }

    private static void validateVenueFields(String name, String address, int capacity, Set<Screening> screenings) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required and cannot be blank");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address is required and cannot be blank");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        if (screenings != null && screenings.isEmpty()) {
            throw new IllegalArgumentException("Screenings set cannot be empty if provided");
        }
    }
}