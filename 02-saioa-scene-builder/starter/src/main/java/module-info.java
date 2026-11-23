module eus.liburutegia {
    requires javafx.controls;
    requires javafx.fxml;

    exports eus.liburutegia;

    // FXMLLoaderrek isla (reflection) bidez sartu behar du @FXML eremu pribatuetara.
    opens eus.liburutegia.controller to javafx.fxml;
}
