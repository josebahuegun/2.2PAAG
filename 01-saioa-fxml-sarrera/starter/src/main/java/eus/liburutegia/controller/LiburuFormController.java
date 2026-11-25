
package eus.liburutegia.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LiburuFormController {

    @FXML
    private TextField txtIzenburua;

    @FXML
    private TextField txtEgilea;

    @FXML
    private TextField txtUrtea;

    @FXML
    private Label lblMezua;

    @FXML
    private void gorde() {

        String izenburua = txtIzenburua.getText();
        String egilea = txtEgilea.getText();
        String urtea = txtUrtea.getText();

        lblMezua.setText(
                "Liburua gordeta: " + izenburua +
                        " - " + egilea + " - " + urtea
        );
    }
}
