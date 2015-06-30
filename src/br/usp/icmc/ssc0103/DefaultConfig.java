package br.usp.icmc.ssc0103;

import br.usp.icmc.ssc0103.fxmlcontrollers.ProductDialogControls;
import br.usp.icmc.ssc0103.fxmlcontrollers.ServerGUIControls;
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
                        "fxmldefinitions/ServerGUI.fxml"
                ),
                              primaryStage);
    }

    public FXMLController serverGUIControls()
    {
        return new ServerGUIControls();
    }

    public FXMLWindow productDialog()
    {
        return new FXMLWindow(productDialogControls(), getClass().getResource
                (
                        "fxmldefinitions/ProductDialog.fxml"
                ),
                              primaryStage);
    }

    public FXMLController productDialogControls()
    {
        return new ProductDialogControls();
    }
}
