package br.usp.icmc.ssc0103.control;

import br.usp.icmc.ssc0103.DefaultConfig;
import br.usp.icmc.ssc0103.net.ClientContext;
import br.usp.icmc.ssc0103.wrap.FXMLController;
import br.usp.icmc.ssc0103.wrap.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

// Client view startup window dialog controller

// Method names are sufficiently self-descriptive

public class SetRemoteController implements FXMLController
{
    @FXML
    private TextField remoteAddress;
    @FXML
    private TextField remotePort;
    @FXML
    private Text      oneLiner;

    private FXMLWindow view;

    @FXML
    public void plugRemote(ActionEvent actionEvent)
    {
        ClientContext.getContext().connect(remoteAddress.getText(),
                                           remotePort.getText());

        DefaultConfig.getConfig().clientGUI().show();
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
