package br.usp.icmc.ssc0103;

import br.usp.icmc.ssc0103.fxmlcontrollers.*;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLController;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLWindow;
import javafx.stage.Stage;

public class DefaultConfig
{
    private static final DefaultConfig CONFIG = new DefaultConfig();

    private Stage primaryStage;

    public DefaultConfig() { }

    public static DefaultConfig getConfig() { return CONFIG; }

    public void setPrimaryStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public FXMLWindow serverGUI()
    {
        return new FXMLWindow(serverGUIController(), getClass()
                .getResource("fxmldefinitions/server_mw.fxml"), primaryStage);
    }

    public FXMLWindow serverDB1()
    {
        return new FXMLWindow(serverDB1Controller(), getClass()
                .getResource("fxmldefinitions/server_db1.fxml"), primaryStage);
    }

    public FXMLWindow serverDB2()
    {
        return new FXMLWindow(serverDB2Controller(), getClass()
                .getResource("fxmldefinitions/server_db2.fxml"), primaryStage);
    }

    public FXMLWindow clientGUI()
    {
        return new FXMLWindow(clientGUIController(), getClass()
                .getResource("fxmldefinitions/client_mw.fxml"), primaryStage);
    }

    public FXMLWindow setRemote()
    {
        return new FXMLWindow(setRemoteController(), getClass()
                .getResource("fxmldefinitions/set_remote.fxml"), primaryStage);
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

    public FXMLController setRemoteController()
    {
        return new SetRemoteController();
    }
}
