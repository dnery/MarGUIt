package br.usp.icmc.ssc0103;

import br.usp.icmc.ssc0103.fxmlcontrollers.ServerDB1Controller;
import br.usp.icmc.ssc0103.fxmlcontrollers.ServerMWController;
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
        return new FXMLWindow(serverGUIControls(), getClass().getResource
                (
                        "fxmldefinitions/server_mw.fxml"
                ),
                              primaryStage);
    }

    public FXMLController serverGUIControls()
    {
        return new ServerMWController();
    }

    public FXMLWindow productDialog()
    {
        return new FXMLWindow(productDialogControls(), getClass().getResource
                (
                        "fxmldefinitions/server_db1.fxml"
                ),
                              primaryStage);
    }

    public FXMLController productDialogControls()
    {
        return new ServerDB1Controller();
    }
}
