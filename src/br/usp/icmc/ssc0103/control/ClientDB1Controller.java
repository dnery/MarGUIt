package br.usp.icmc.ssc0103.control;

import br.usp.icmc.ssc0103.net.ClientContext;
import br.usp.icmc.ssc0103.net.SMessage;
import br.usp.icmc.ssc0103.wrap.FXMLController;
import br.usp.icmc.ssc0103.wrap.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

// Client view "Register Customer" dialog controller

// Method names are sufficiently self-descriptive

public class ClientDB1Controller implements FXMLController
{
    @FXML
    private TextField     nameField;
    @FXML
    private TextField     addressField;
    @FXML
    private TextField     phoneNumberField;
    @FXML
    private TextField     userEmailField;
    @FXML
    private TextField     userNameField;
    @FXML
    private PasswordField passWordField;
    @FXML
    private Text          oneLiner;

    private FXMLWindow view;

    @FXML
    public void registerCustomer(ActionEvent actionEvent)
    {
        SMessage message = new SMessage("adduser," +
                                        nameField.getText() + "," +
                                        addressField.getText() + "," +
                                        phoneNumberField.getText() + "," +
                                        userEmailField.getText() + "," +
                                        userNameField.getText() + "," +
                                        passWordField.getText());
        ClientContext.getContext().setInput(message);
        ClientContext.getContext().remoteWrite();

        actionEvent.consume();
        close();
    }

    @Override
    public void setView(FXMLWindow view)
    {
        this.view = view;
    }

    @FXML
    public void close()
    {
        view.close();
    }
}
