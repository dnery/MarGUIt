package br.usp.icmc.ssc0103;

import javafx.application.Application;
import javafx.stage.Stage;

public class ClientApp extends Application
{
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Client context
        DefaultDatabase.getInstance();
        DefaultConfig.getConfig().setPrimaryStage(primaryStage);
        DefaultConfig.getConfig().setRemote().show();
    }
}
