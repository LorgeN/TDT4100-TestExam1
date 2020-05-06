package bike;

import bike.rental.PricePolicy;
import bike.rental.RentalSession;

import java.time.Duration;
import java.time.LocalDateTime;

public class DefaultPricePolicy implements PricePolicy {

    private static final int DEFAULT_PRICE_PER_RENTAL = 0;
    private static final int DEFAULT_PRICE_PER_HOUR = 10;
    private static final int DEFAULT_PRICE_PER_EXTENSION = 5;
    private static final int DEFAULT_PRICE_OVERDUE = 10;

    private final int pricePerRental;
    private final int pricePerHour;
    private final int pricePerExtension;
    private final int priceOverdue;

    public DefaultPricePolicy() {
        this(DEFAULT_PRICE_PER_RENTAL, DEFAULT_PRICE_PER_HOUR, DEFAULT_PRICE_PER_EXTENSION, DEFAULT_PRICE_OVERDUE);
    }

    public DefaultPricePolicy(int pricePerRental, int pricePerHour, int pricePerExtension, int priceOverdue) {
        this.pricePerRental = pricePerRental;
        this.pricePerHour = pricePerHour;
        this.pricePerExtension = pricePerExtension;
        this.priceOverdue = priceOverdue;
    }

    @Override
    public int calculatePrice(Person person, RentalSession session, LocalDateTime returnTime) {
        int price = this.pricePerRental;

        // Check if bike is being returned late
        if (returnTime.isAfter(session.getRentalEnd())) {
            price += this.priceOverdue;
        }

        // Add cost for late extensions
        price += session.getLateExtensions() * this.priceOverdue;

        // Add cost for extensions
        price += session.getExtensions() * this.pricePerExtension;

        // Add hourly cost
        price += this.computeHours(session.getRentalStart(), returnTime) * this.pricePerHour;

        return price;
    }

    private int computeHours(LocalDateTime startTime, LocalDateTime endTime) {
        Duration between = Duration.between(startTime, endTime);
        // Check toMinutesPart() greater than 0 to see if they have started another hour. This basically
        // means that 01:00:59 (HH:MM:SS) will count as one hour, while 01:01:00 counts as 2 hours.
        return (int) between.toHours() + (between.toMinutesPart() > 0 ? 1 : 0);
    }
}
