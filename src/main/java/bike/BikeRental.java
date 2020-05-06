package bike;

import bike.rental.ItemRental;
import bike.rental.PricePolicy;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BikeRental extends ItemRental<Bike> {

    // Rental bikes are stored in the super class

    // Both variables are contained using a set. We are using a set instead of a list to avoid allowing
    // duplicates. For example, we do not need to register the same bike twice.

    // Stores all rental locations using their GeoLocation. This representation has been chosen
    // because the rental locations do not move and do not have any special attributes to them that
    // require any other sort of wrapper around them. We simply need to know where the location is.
    //
    // For a more complex implementation it could be useful to for example have a name for the station.
    private final Set<GeoLocation> rentalLocations;

    public BikeRental() {
        this(new DefaultPricePolicy());
    }

    public BikeRental(PricePolicy policy) {
        super(policy);
        this.rentalLocations = new HashSet<>();
    }

    public void addLocation(GeoLocation location) {
        this.rentalLocations.add(location);
    }

    /**
     * Counts the number of available bikes within a certain distance of a provided location.
     *
     * @param location The {@link GeoLocation location}
     * @param distance The maximum distance (Inclusive) from the location
     * @return the number of available bikes within a certain distance of a provided location
     */
    public int countAvailableBikesNearby(GeoLocation location, double distance) {
        return (int) this.getItems().stream()
          .filter(bike -> bike.getLocation().distance(location) <= distance)
          .count();
    }

    /**
     * Finds the closest station (location) within the provided (maximum) distance of the provided bike
     *
     * @param bike        The {@link Bike bike}
     * @param maxDistance The maximum distance from the provided bike
     * @return the closest station (location) within the provided (maximum) distance of the provided bike
     */
    private GeoLocation getStationNearby(Bike bike, double maxDistance) {
        return this.rentalLocations.stream()
          .filter(location -> location.distance(bike.getLocation()) <= maxDistance)
          .min(Comparator.comparingDouble(loc -> loc.distance(bike.getLocation())))
          .orElse(null); // Could alternatively throw an exception here
    }

    /**
     * @return the bikes that currently are rented
     */
    public Collection<Bike> getRentedBikes() {
        return this.getRentedItems();
    }

    /**
     * @return the bikes that are close to a station (within 30 meters), but still are rented
     */
    public Collection<Bike> getUnreturnedBikes() {
        return this.getItems().stream()
          .filter(Bike::isRented)
          .filter(bike -> this.getStationNearby(bike, 30) != null)
          .collect(Collectors.toList());
    }

    /**
     * Called when a person starts renting a bike by taking it from a station.
     * Checks the arguments before registering all necessary info of the rental.
     *
     * @param person      The {@link Person person} renting the bike
     * @param rentalStart the start time of the rental
     * @param rentalEnd   the expected return time
     * @throws IllegalArgumentException if the rentalStart isn't before rentalEnd
     * @throws IllegalStateException    if the bike isn't available for rental
     */
    public void rentBike(Person person, Bike bike, LocalDateTime rentalStart, LocalDateTime rentalEnd) {
        this.rentItem(person, bike, rentalStart, rentalEnd);
    }

    /**
     * Called when a person returns a bike.
     * Checks the arguments, computes the price, performs the payment and clears the rental info.
     * Note that if the payment isn't completed, the rental info should not be cleared.
     *
     * @param person The {@link Person person} returning the bike
     * @param bike   The {@link Bike bike} being returned
     * @param now    The time the bike is returned
     * @throws IllegalStateException if the bike isn't currently being rented by the person argument
     * @throws IllegalStateException if person isn't near (within 30 meters of) a station
     * @throws IllegalStateException if the person can't afford to pay
     */
    public void returnBike(Person person, Bike bike, LocalDateTime now) {
        GeoLocation nearby = this.getStationNearby(bike, 30);
        if (nearby == null) {
            throw new IllegalStateException("Not near a station!");
        }

        this.returnItem(person, bike, now);
    }

    public static void main(final String[] args) {
        // Some geo-locations to use in testing:
        // In the hall outside F1: 63.416522, 10.403345
        // By the entrance to Realfagsbygget closest to F1: 63.416017, 10.404729
        // Another spot by the same entrance, closer than 30 meters: 63.416079, 10.404565
        System.out.println(GeoLocation.distance(63.416017, 10.404729, 63.416079, 10.404565));
    }
}
