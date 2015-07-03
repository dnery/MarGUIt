package br.usp.icmc.ssc0103.fxmlcontrollers;

import br.usp.icmc.ssc0103.ClientContext;
import br.usp.icmc.ssc0103.DefaultConfig;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLController;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SetRemoteController implements FXMLController
{
    @FXML
    private TextField remoteAddress;
    @FXML
    private TextField remotePort;
    @FXML
    private Text      oneLiner;

    private FXMLWindow view;

    @Override
    public void setView(FXMLWindow view)
    {
        this.view = view;
    }

    @FXML
    public void plugRemote(ActionEvent actionEvent)
    {
        ClientContext.getContext().connect(remoteAddress.getText(),
                                           remotePort.getText());

        DefaultConfig.getConfig().clientGUI().show();
        actionEvent.consume();
        close();
    }

    @FXML
    public void close()
    {
        view.close();
    }
}
