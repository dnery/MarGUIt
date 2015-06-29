package br.usp.icmc.ssc0103;

import br.usp.icmc.ssc0103.AbstractResources.FXMLController;
import br.usp.icmc.ssc0103.ConcreteResources.FXMLWindow;
import br.usp.icmc.ssc0103.Controls.ServerGUIController;
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
        return new FXMLWindow(serverGUIController(),
                              getClass().getResource("Views/ServerGUI.fxml"),
                              primaryStage);
    }

    public FXMLController serverGUIController()
    {
        return new ServerGUIController();
    }
}
