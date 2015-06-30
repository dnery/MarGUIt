package br.usp.icmc.ssc0103.fxmlcontrollers;

import br.usp.icmc.ssc0103.DefaultDatabase;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLController;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ProductDialogControls implements FXMLController
{
    @FXML
    public TextField nameField;
    @FXML
    public TextField costField;
    @FXML
    public TextField providerField;
    @FXML
    public TextField expiryField;
    @FXML
    public TextField amountField;

    @FXML
    public Text alertOneLiner;

    private FXMLWindow view;

    @Override
    public void setView(FXMLWindow view) { this.view = view; }

    @FXML
    public void close() { view.close(); }

    public void closeDialog(ActionEvent actionEvent)
    {
        try {
            DefaultDatabase.getInstance().newProduct(nameField.getText(),
                                                     costField.getText(),
                                                     providerField.getText(),
                                                     expiryField.getText(),
                                                     amountField.getText());
            actionEvent.consume();
            close();
        } catch (Exception e) {
            alertOneLiner.setText("Parsing error; Check your input...");
        }
    }
}
