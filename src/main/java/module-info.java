module kont {

    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    exports bike;
    exports bike.rental;

    opens bike to javafx.fxml;
}
