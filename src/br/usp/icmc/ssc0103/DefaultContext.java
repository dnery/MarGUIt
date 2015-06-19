package br.usp.icmc.ssc0103;

import com.googlecode.lanterna.gui.Window;

import java.net.ServerSocket;

public class DefaultContext
{
    private Window       defaultWindow;
    private ServerSocket defaultSSocket;

    // Eager singleton initializer
    private static final DefaultContext CONTEXT = new DefaultContext();

    // Eager singleton constructor
    private DefaultContext() {}

    // Eager singleton context getter
    public static DefaultContext getContext() { return CONTEXT; }

    public Window getDefaultWindow() { return defaultWindow; }

    public ServerSocket getDefaultSSocket() { return defaultSSocket; }

    public void setDefaultWindow(Window defaultWindow)
    {
        this.defaultWindow = defaultWindow;
    }

    public void setDefaultSSocket(ServerSocket defaultSSocket)
    {
        this.defaultSSocket = defaultSSocket;
    }
}
