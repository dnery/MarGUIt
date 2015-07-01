package br.usp.icmc.ssc0103.fxmlcontrollers;

import br.usp.icmc.ssc0103.fxmlwrappers.FXMLController;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import static br.usp.icmc.ssc0103.DefaultDatabase.getInstance;

public class ServerDB1Controller implements FXMLController
{
    @FXML
    private TextField nameField;
    @FXML
    private TextField costField;
    @FXML
    private TextField providerField;
    @FXML
    private TextField expiryField;
    @FXML
    private TextField amountField;
    @FXML
    private Text      oneLiner;

    private FXMLWindow view;

    @Override
    public void setView(FXMLWindow view) { this.view = view; }

    @FXML
    public void registerProduct(ActionEvent actionEvent)
    {
        try {
            getInstance().newProduct(nameField.getText(),
                                     costField.getText(),
                                     providerField.getText(),
                                     expiryField.getText(),
                                     amountField.getText());
            actionEvent.consume();
            close();
        } catch (Exception e) {
            oneLiner.setText("Parsing error; Check your input...");
        }
    }

    @FXML
    public void close() { view.close(); }
}
