package br.usp.icmc.ssc0103.fxmlcontrollers;

import br.usp.icmc.ssc0103.Message;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLController;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLWindow;
import br.usp.icmc.ssc0103.models.ProductEntry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import static br.usp.icmc.ssc0103.ClientContext.getContext;

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
        makePurchaseButton.setDisable(true);
    }

    @Override
    public void setView(FXMLWindow view)
    {
        this.view = view;
    }

    public void registerUserAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Registering New User...");
    }

    public void makePurchaseAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Making a Purchase...");
    }

    public void updateViewAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Updating Tables...");

        Message message = new Message("getproducts");
        getContext().setInput(message);
        getContext().remoteWrite();
    }

    public void quitAppAction(ActionEvent actionEvent)
    {
        actionEvent.consume();
        close();
    }

    public void logInAction(ActionEvent actionEvent)
    {
        oneLiner.setText("Logging in...");
    }

    @FXML
    public void close()
    {
        view.close();
    }
}
