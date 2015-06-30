package br.usp.icmc.ssc0103.fxmlcontrollers;

import br.usp.icmc.ssc0103.fxmlwrappers.FXMLController;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class ServerGUIControls implements FXMLController
{
    @FXML
    public TableView productTableView;
    @FXML
    public Text      debugLineView;

    private FXMLWindow view;

    @Override
    public void setView(FXMLWindow view) { this.view = view; }

    @FXML
    void closeView() { view.close(); }

    @FXML
    public void registerProductAction(ActionEvent actionEvent)
    {
        debugLineView.setText("Registering New Product...");
    }

    @FXML
    public void removeProductAction(ActionEvent actionEvent)
    {
        debugLineView.setText("Removing Existing Product...");
    }

    @FXML
    public void quitAppAction(ActionEvent actionEvent)
    {
        actionEvent.consume();
        closeView();
    }

    @FXML
    public void dumpLogsAction(ActionEvent actionEvent)
    {
        debugLineView.setText("Dumping Logs to Disk...");
    }

    @FXML
    public void serverInfoAction(ActionEvent actionEvent)
    {
        debugLineView.setText("Display Listener info...");
    }
}
