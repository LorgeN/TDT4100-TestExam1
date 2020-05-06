package bike;

import bike.rental.RentalItem;

import java.util.Objects;

public class Bike extends RentalItem {

    private GeoLocation location;

    public Bike(GeoLocation location) {
        this.location = location;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Bike)) {
            return false;
        }

        Bike bike = (Bike) o;
        return Objects.equals(location, bike.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "Bike{" +
          "location=" + location +
          '}';
    }
}
