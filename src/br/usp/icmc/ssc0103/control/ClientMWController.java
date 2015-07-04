package br.usp.icmc.ssc0103.control;

import br.usp.icmc.ssc0103.DefaultConfig;
import br.usp.icmc.ssc0103.model.ProductEntry;
import br.usp.icmc.ssc0103.net.SMessage;
import br.usp.icmc.ssc0103.wrap.FXMLController;
import br.usp.icmc.ssc0103.wrap.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import static br.usp.icmc.ssc0103.DefaultDatabase.getInstance;
import static br.usp.icmc.ssc0103.net.ClientContext.getContext;

// Client view main window dialog controller

// Method names are sufficiently self-descriptive

public class ClientMWController implements FXMLController
{
    @FXML
    private TableView<ProductEntry> productTableView;
    @FXML
    private Button                  makePurchaseButton;
    @FXML
    private Button                  updateViewButton;
    @FXML
    private Button                  logInButton;
    @FXML
    private Text                    oneLiner;

    private FXMLWindow view;

    @FXML
    void initialize()
    {
        productTableView.setItems(getInstance().getObservableProducts());
        makePurchaseButton.setDisable(true);
    }

    @FXML
    public void registerUserAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Registering New User...");

        DefaultConfig.getConfig().clientDB1().show();
    }

    @FXML
    public void makePurchaseAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Making a Purchase...");
    }

    @FXML
    public void updateViewAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Updating Tables...");

        SMessage message = new SMessage("getproducts");
        getContext().setInput(message);
        getContext().remoteWrite();
    }

    @FXML
    public void quitAppAction(ActionEvent actionEvent)
    {
        actionEvent.consume();
        close();
    }

    @FXML
    public void logInAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Logging in...");
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
