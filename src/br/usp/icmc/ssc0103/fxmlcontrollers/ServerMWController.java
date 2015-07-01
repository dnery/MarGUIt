package br.usp.icmc.ssc0103.fxmlcontrollers;

import br.usp.icmc.ssc0103.DefaultConfig;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLController;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLWindow;
import br.usp.icmc.ssc0103.models.ProductEntry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import static br.usp.icmc.ssc0103.DefaultDatabase.getInstance;

public class ServerMWController implements FXMLController
{
    @FXML
    private TableView<ProductEntry> productTableView;
    @FXML
    private TableColumn             nameColumn;
    @FXML
    private Text                    oneLiner;

    private FXMLWindow view;

    @FXML
    void initialize()
    {
        productTableView.setItems(getInstance().getObservableProducts());
    }

    @Override
    public void setView(FXMLWindow view) { this.view = view; }

    @FXML
    public void registerProductAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Registering New Product...");

        DefaultConfig.getConfig().serverDB1().show();
    }

    @FXML
    public void removeProductAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Removing Existing Product...");
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
        oneLiner.setText("Dumping Logs to Disk...");
    }

    @FXML
    public void serverInfoAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Display Listener info...");

        DefaultConfig.getConfig().serverDB2().show();
    }

    @FXML
    public void close() { view.close(); }
}
