package bike.rental;

import bike.Person;

import java.time.LocalDateTime;

public interface PricePolicy {

    int calculatePrice(Person person, RentalSession session, LocalDateTime returnTime);
}
