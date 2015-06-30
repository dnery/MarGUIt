package br.usp.icmc.ssc0103.fxmlcontrollers;

import br.usp.icmc.ssc0103.DefaultConfig;
import br.usp.icmc.ssc0103.DefaultDatabase;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLController;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLWindow;
import br.usp.icmc.ssc0103.models.ProductEntry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class ServerGUIControls implements FXMLController
{
    @FXML
    public TableView<ProductEntry> productTableView;

    @FXML
    public Text alertOneLiner;

    private FXMLWindow view;

    @Override
    public void setView(FXMLWindow view) { this.view = view; }

    @FXML
    public void close() { view.close(); }

    @FXML
    public void registerProductAction(ActionEvent actionEvent)
    {
        alertOneLiner.setText("Registering New Product...");

        DefaultConfig.getConfig().productDialog().show();
    }

    @FXML
    public void removeProductAction(ActionEvent actionEvent)
    {
        alertOneLiner.setText("Removing Existing Product...");
    }

    @FXML
    public void quitAppAction(ActionEvent actionEvent)
    {
        actionEvent.consume();
        close();
    }

    @FXML
    public void dumpLogsAction(ActionEvent actionEvent)
    {
        alertOneLiner.setText("Dumping Logs to Disk...");
    }

    @FXML
    public void serverInfoAction(ActionEvent actionEvent)
    {
        alertOneLiner.setText("Display Listener info...");
    }
}
