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
            DefaultContext.getContext().setDefaultSSocket(new ServerSocket(0));

            screen.getScreen().startScreen();
            DefaultContext.getContext().setDefaultParent(screen);
            (new ManagementView("Server side management")).display();
            screen.getScreen().stopScreen();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }
}
