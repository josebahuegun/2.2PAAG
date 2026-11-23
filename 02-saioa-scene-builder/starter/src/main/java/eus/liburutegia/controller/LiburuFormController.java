package eus.liburutegia.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LiburuFormController {

    // Eremuen izenak FXMLko fx:id-ekin bat etorri behar dira.
    @FXML private TextField izenburuaField;
    @FXML private TextField egileaField;
    @FXML private TextField urteaField;
    @FXML private Label mezuaLabel;

    // FXMLLoaderrek deitzen du, eremu guztiak injektatu ondoren.
    @FXML
    private void initialize() {
        mezuaLabel.setText("Bete datuak eta sakatu Gorde.");
    }

    // FXMLan: onAction="#gorde"
    @FXML
    private void gorde() {
        String urtea = urteaField.getText().trim();
        if (!urtea.matches("\\d{1,4}")) {      // Azkarrentzako zatia
            mezuaLabel.setText("Errorea: urteak zenbakia izan behar du.");
            return;
        }
        mezuaLabel.setText("Gordeta: " + izenburuaField.getText()
                + " (" + egileaField.getText() + ", " + urtea + ")");
    }
}
