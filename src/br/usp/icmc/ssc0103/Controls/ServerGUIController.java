package br.usp.icmc.ssc0103.Controls;

import br.usp.icmc.ssc0103.AbstractResources.FXMLController;
import br.usp.icmc.ssc0103.ConcreteResources.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ServerGUIController implements FXMLController
{
    @FXML
    public Button debugButton;
    @FXML
    public Button quitButton;
    @FXML
    public Text outputText;

    private FXMLWindow view;

    @Override
    public void setView(FXMLWindow view) { this.view = view; }

    @FXML
    void closeView() { view.close(); }

    @FXML
    public void showMessage(ActionEvent actionEvent)
    {
        System.out.println("Hello There!");
        outputText.setText("Printing!");
    }

    @FXML
    public void quitProgram(ActionEvent actionEvent)
    {
        outputText.setText("Quitting!");
        actionEvent.consume();
        closeView();
    }
}
