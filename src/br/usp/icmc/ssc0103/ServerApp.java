package br.usp.icmc.ssc0103;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;

import java.net.ServerSocket;

public class ServerApp
{
    static GUIScreen screen = TerminalFacade.createGUIScreen();

    public static void main(String[] args)
    {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            DefaultContext.getContext().setDefaultSSocket(serverSocket);

            screen.getScreen().startScreen();

            ManagementView managementView = new ManagementView("Server management");
            DefaultContext.getContext().setDefaultWindow(managementView);
            DefaultController.getController().engageManagementControls();
            screen.showWindow(managementView, GUIScreen.Position.CENTER);

            screen.getScreen().stopScreen();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }
}
