package bike.rental;

import bike.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An item which a {@link Person person} may rent for a period of time
 */
public abstract class RentalItem {

    private final List<RentalSession> previousSessions;
    private RentalSession currentSession;

    public RentalItem() {
        this.previousSessions = new ArrayList<>();
    }

    public RentalSession getRentalSession() {
        return this.currentSession;
    }

    public List<RentalSession> getPreviousSessions() {
        return Collections.unmodifiableList(this.previousSessions);
    }

    public boolean isRented() {
        return this.getRentalSession() != null;
    }

    public Person getRenter() {
        if (!this.isRented()) {
            throw new IllegalStateException("Item is not rented!");
        }

        return this.currentSession.getRenter();
    }

    public LocalDateTime getRentalStart() {
        if (!this.isRented()) {
            throw new IllegalStateException("Item is not rented!");
        }

        return this.currentSession.getRentalStart();
    }

    public LocalDateTime getRentalEnd() {
        if (!this.isRented()) {
            throw new IllegalStateException("Item is not rented!");
        }

        return this.currentSession.getRentalEnd();
    }

    public int getExtensions() {
        if (!this.isRented()) {
            throw new IllegalStateException("Item is not rented!");
        }

        return this.currentSession.getExtensions();
    }

    public int getLateExtensions() {
        if (!this.isRented()) {
            throw new IllegalStateException("Item is not rented!");
        }

        return this.currentSession.getLateExtensions();
    }

    public void setRenter(Person renter, LocalDateTime rentalStart, LocalDateTime rentalEnd) {
        if (!this.isRented()) {
            throw new IllegalStateException("Item is not rented!");
        }

        this.currentSession = new RentalSession(renter, rentalStart, rentalEnd);
    }

    public void extendRental(LocalDateTime extensionStart, LocalDateTime rentalEnd) {
        if (!this.isRented()) {
            throw new IllegalStateException("Item is not rented!");
        }

        this.currentSession.extendRental(extensionStart, rentalEnd);
    }

    public void endRental() {
        if (!this.isRented()) {
            throw new IllegalStateException("Item is not rented!");
        }

        this.previousSessions.add(this.currentSession);
        this.currentSession = null;
    }
}
