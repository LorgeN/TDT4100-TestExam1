package bike;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalTimeStringConverter;

public class BikeRentalController {

	private final BikeRental bikeRental;

	public BikeRentalController() {
		bikeRental = new BikeRental();
		// TODO: initialise bikeRental
	}

	// hint: LocalTimeStringConverter has two useful method, fromString(String) and toString(LocalTime)
	private final LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter();

	@FXML
	public void initialize() {
		// TODO: initialise ui stuff
	}

	@FXML
	private TextField fromInput;

	@FXML
	private TextField toInput;

	// TODO: handle button actions
	// hint: use plus and minus methods of LocalTime class and ChronoUnit.HOURS
}
