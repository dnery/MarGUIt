package br.usp.icmc.ssc0103;

import br.usp.icmc.ssc0103.control.*;
import br.usp.icmc.ssc0103.wrap.FXMLController;
import br.usp.icmc.ssc0103.wrap.FXMLWindow;
import javafx.stage.Stage;

// FXML loader wrappers
public class DefaultConfig
{
    // Eager singleton initializer
    private static final DefaultConfig CONFIG = new DefaultConfig();

    // Attributes
    private Stage primaryStage;

    // Eager singleton constructor
    public DefaultConfig() { }

    // Eager singleton context getter
    public static DefaultConfig getConfig() { return CONFIG; }

    public void setPrimaryStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public FXMLWindow serverGUI()
    {
        return new FXMLWindow(serverGUIController(), getClass()
                .getResource("fxml/server_mw.fxml"), primaryStage);
    }

    public FXMLWindow serverDB1()
    {
        return new FXMLWindow(serverDB1Controller(), getClass()
                .getResource("fxml/server_db1.fxml"), primaryStage);
    }

    public FXMLWindow serverDB2()
    {
        return new FXMLWindow(serverDB2Controller(), getClass()
                .getResource("fxml/server_db2.fxml"), primaryStage);
    }

    public FXMLWindow clientGUI()
    {
        return new FXMLWindow(clientGUIController(), getClass()
                .getResource("fxml/client_mw.fxml"), primaryStage);
    }

    public FXMLWindow clientDB1()
    {
        return new FXMLWindow(clientDB1Controller(), getClass()
                .getResource("fxml/client_db1.fxml"), primaryStage);
    }

    public FXMLWindow setRemote()
    {
        return new FXMLWindow(setRemoteController(), getClass()
                .getResource("fxml/set_remote.fxml"), primaryStage);
    }

    public FXMLController serverGUIController()
    {
        return new ServerMWController();
    }

    public FXMLController serverDB1Controller()
    {
        return new ServerDB1Controller();
    }

    public FXMLController serverDB2Controller()
    {
        return new ServerDB2Controller();
    }

    public FXMLController clientGUIController()
    {
        return new ClientMWController();
    }

    public FXMLController clientDB1Controller()
    {
        return new ClientDB1Controller();
    }

    public FXMLController setRemoteController()
    {
        return new SetRemoteController();
    }
}
