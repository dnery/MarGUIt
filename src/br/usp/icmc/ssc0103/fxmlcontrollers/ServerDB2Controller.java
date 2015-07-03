package br.usp.icmc.ssc0103.fxmlcontrollers;

import br.usp.icmc.ssc0103.ServerContext;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLController;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
                                               .getListener()
                                               .getServerSocket()
                                               .getLocalPort() + "");
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void setView(FXMLWindow view) { this.view = view; }

    @FXML
    public void closeDialog(ActionEvent actionEvent)
    {
        actionEvent.consume();
        close();
    }

    @FXML
    public void close() { view.close(); }

}
