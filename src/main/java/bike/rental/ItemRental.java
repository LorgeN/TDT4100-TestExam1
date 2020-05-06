package bike.rental;

import bike.Bike;
import bike.Person;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Some form of rental "agency" which can rent out a certain type of {@link RentalItem item}
 *
 * @param <T> The type of item. This can be constrained or can be left as RentalItem to allow
 *            all kinds of different rentals
 */
public class ItemRental<T extends RentalItem> {

    // Store items using the RentalItem wrapper object because that is what it was made for.
    public final Set<T> items;
    public final PricePolicy pricePolicy;

    public ItemRental(PricePolicy policy) {
        this.pricePolicy = policy;
        this.items = new HashSet<>();
    }

    /**
     * Adds an item that is available to be rented
     *
     * @param item The new {@link RentalItem item}
     */
    public void addItem(T item) {
        this.items.add(item);
    }

    /**
     * @return All items managed by this rental "agency"
     */
    public Set<T> getItems() {
        return Collections.unmodifiableSet(this.items);
    }

    /**
     * @return the items that currently are rented
     */
    public Collection<T> getRentedItems() {
        return this.items.stream().filter(RentalItem::isRented).collect(Collectors.toList());
    }

    /**
     * Called when a person starts renting a item by taking it from a station.
     * Checks the arguments before registering all necessary info of the rental.
     *
     * @param item        The {@link RentalItem item} to rent
     * @param person      The {@link Person person} renting the item
     * @param rentalStart the start time of the rental
     * @param rentalEnd   the expected return time
     * @throws IllegalArgumentException if the rentalStart isn't before rentalEnd
     * @throws IllegalStateException    if the item isn't available for rental
     */
    public void rentItem(Person person, T item, LocalDateTime rentalStart, LocalDateTime rentalEnd) {
        if (!rentalStart.isBefore(rentalEnd)) {
            throw new IllegalArgumentException("Rental start is not before rental end!");
        }

        if (item.isRented()) {
            throw new IllegalStateException("Item is already rented!");
        }

        item.setRenter(person, rentalStart, rentalEnd);
    }

    /**
     * Called when a person extends an ongoing item rental.
     * Checks the arguments before registering all necessary info of the rental extension.
     *
     * @param person         The {@link Person person} who is currently renting the item
     * @param item           The {@link Bike item} being rented
     * @param extensionStart the time the extension starts
     * @param rentalEnd      the (new) expected return time
     * @throws IllegalArgumentException if the extensionStart isn't before rentalEnd
     * @throws IllegalStateException    if person isn't currently renting the item
     * @throws IllegalStateException    if the item isn't currently being rented
     */
    public void extendRental(Person person, T item, LocalDateTime extensionStart, LocalDateTime rentalEnd) {
        if (!extensionStart.isBefore(rentalEnd)) {
            throw new IllegalArgumentException("Rental start is not before rental end!");
        }

        if (!item.isRented() || !item.getRenter().equals(person)) {
            throw new IllegalStateException("Item is not rented or not rented by given person!");
        }

        item.extendRental(extensionStart, rentalEnd);
    }

    /**
     * Called when a person returns a item.
     * Checks the arguments, computes the price, performs the payment and clears the rental info.
     * Note that if the payment isn't completed, the rental info should not be cleared.
     *
     * @param person    The {@link Person person} returning the item
     * @param item      The {@link Bike item} being returned
     * @param rentalEnd the time the item is returned
     * @throws IllegalStateException if the item isn't currently being rented by the person argument
     * @throws IllegalStateException if the person can't afford to pay
     */
    public void returnItem(Person person, T item, LocalDateTime rentalEnd) {
        if (!item.isRented() || !item.getRenter().equals(person)) {
            throw new IllegalStateException("Item is not rented or not rented by given person!");
        }

        int price = this.pricePolicy.calculatePrice(person, item.getRentalSession(), rentalEnd);
        if (price > person.getBalance()) {
            throw new IllegalStateException("Person " + person + " can't afford this!");
        }

        person.setBalance(person.getBalance() - price);
        item.endRental();
    }
}
