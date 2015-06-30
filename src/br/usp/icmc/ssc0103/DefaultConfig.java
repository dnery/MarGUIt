package br.usp.icmc.ssc0103;

import br.usp.icmc.ssc0103.fxmlcontrollers.ServerGUIControls;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLController;
import br.usp.icmc.ssc0103.fxmlwrappers.FXMLWindow;
import javafx.stage.Stage;

public class DefaultConfig
{
    private static final DefaultConfig config = new DefaultConfig();

    private Stage primaryStage;

    public DefaultConfig() { }

    public static DefaultConfig getConfig() { return config; }

    public void setPrimaryStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public FXMLWindow serverGUI()
    {
        return new FXMLWindow(serverGUIController(), getClass()
                .getResource("fxmldefinitions/ServerGUI.fxml"), primaryStage);
    }

    public FXMLController serverGUIController()
    {
        return new ServerGUIControls();
    }
}
