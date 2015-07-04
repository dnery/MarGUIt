package br.usp.icmc.ssc0103.control;

import br.usp.icmc.ssc0103.net.ServerContext;
import br.usp.icmc.ssc0103.wrap.FXMLController;
import br.usp.icmc.ssc0103.wrap.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.net.InetAddress;
import java.net.UnknownHostException;

// Server view "Server Info" dialog controller

// Method names are sufficiently self-descriptive

public class ServerDB2Controller implements FXMLController
{
    @FXML
    private Text hostName;
    @FXML
    private Text hostAddress;
    @FXML
    private Text listeningPort;

    private FXMLWindow view;

    @FXML
    void initialize()
    {
        try {
            hostName.setText(InetAddress.getLocalHost().getCanonicalHostName());

            hostAddress.setText(InetAddress.getLocalHost().getHostAddress());

            listeningPort.setText(ServerContext.getContext()
                                               .getServerSocket()
                                               .getLocalPort() + "");
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void closeDialog(ActionEvent actionEvent)
    {
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
