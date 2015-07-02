package br.usp.icmc.ssc0103.fxmlcontrollers;

import br.usp.icmc.ssc0103.fxmlwrappers.FXMLController;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class ClientMWController implements FXMLController
{
    @FXML
    private Button makePurchaseButton;
    @FXML
    private Button logInButton;

    @FXML
    private TableView productTableView;

    @FXML
    private Text oneLiner;

    private FXMLWindow view;

    @Override
    public void setView(FXMLWindow view)
    {
        this.view = view;
    }

    public void registerUserAction(ActionEvent actionEvent)
    {

    }

    public void makePurchaseAction(ActionEvent actionEvent)
    {

    }

    public void updateViewAction(ActionEvent actionEvent)
    {

    }

    public void quitAppAction(ActionEvent actionEvent)
    {
        actionEvent.consume();
        close();
    }

    public void logInAction(ActionEvent actionEvent)
    {

    }

    @FXML
    public void close()
    {
        view.close();
    }
}
