package bike.rental;

import bike.Person;

import java.time.LocalDateTime;

public class RentalSession {

    private final Person renter;
    private final LocalDateTime rentalStart;
    private LocalDateTime rentalEnd;
    private int extensions;
    private int lateExtensions;

    public RentalSession(Person renter, LocalDateTime rentalStart, LocalDateTime rentalEnd) {
        this.renter = renter;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
    }

    public Person getRenter() {
        return renter;
    }

    public LocalDateTime getRentalStart() {
        return rentalStart;
    }

    public LocalDateTime getRentalEnd() {
        return rentalEnd;
    }

    public int getExtensions() {
        return extensions;
    }

    public int getLateExtensions() {
        return lateExtensions;
    }

    public void extendRental(LocalDateTime extensionStart, LocalDateTime rentalEnd) {
        if (extensionStart.isAfter(this.rentalEnd)) {
            this.lateExtensions++;
        } else {
            this.extensions++;
        }

        this.rentalEnd = rentalEnd;
    }
}
