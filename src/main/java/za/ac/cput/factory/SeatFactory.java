package za.ac.cput.factory;

import za.ac.cput.domain.Seat;
import za.ac.cput.domain.SeatType;
import za.ac.cput.domain.Screening;

public class SeatFactory {

    private static void validateSeat(SeatType type, String row, int number) {
        if (type == null) {
            throw new IllegalArgumentException("Seat type is required");
        }
        if (row == null || row.isEmpty()) {
            throw new IllegalArgumentException("Row is required");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("Seat number must be positive");
        }
    }

    public static Seat createSeat(SeatType type, String row, int number, Screening screening) {
        validateSeat(type, row, number);
        if (screening == null) {
            throw new IllegalArgumentException("Screening is required");
        }

        return new Seat.Builder()
                .setType(type)
                .setRow(row)
                .setNumber(number)
                .setScreening(screening)
                .build();
    }

    public static Seat createSeatWithoutScreening(SeatType type, String row, int number) {
        validateSeat(type, row, number);

        return new Seat.Builder()
                .setType(type)
                .setRow(row)
                .setNumber(number)
                .build();
    }

    public static Seat createSeatWithDefaultType(String row, int number, Screening screening) {
        validateSeat(SeatType.STANDARD, row, number);
        if (screening == null) {
            throw new IllegalArgumentException("Screening is required");
        }

        return new Seat.Builder()
                .setType(SeatType.STANDARD)
                .setRow(row)
                .setNumber(number)
                .setScreening(screening)
                .build();
    }
    // Other factory methods can be added as needed

}