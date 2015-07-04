package br.usp.icmc.ssc0103.control;

import br.usp.icmc.ssc0103.DefaultConfig;
import br.usp.icmc.ssc0103.model.CustomerEntry;
import br.usp.icmc.ssc0103.model.ProductEntry;
import br.usp.icmc.ssc0103.wrap.FXMLController;
import br.usp.icmc.ssc0103.wrap.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import static br.usp.icmc.ssc0103.DefaultDatabase.getInstance;

// Server view main window dialog controller

// Method names are sufficiently self-descriptive

public class ServerMWController implements FXMLController
{
    @FXML
    private TableView<CustomerEntry> customerTableView;
    @FXML
    private TableView<ProductEntry>  productTableView;
    @FXML
    private Text                     oneLiner;

    private FXMLWindow view;

    @FXML
    void initialize()
    {
        customerTableView.setItems(getInstance().getObservableCustomers());
        productTableView.setItems(getInstance().getObservableProducts());
    }

    @FXML
    public void registerProductAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Registering New Product...");

        DefaultConfig.getConfig().serverDB1().show();
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
