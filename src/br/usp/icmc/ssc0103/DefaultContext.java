package br.usp.icmc.ssc0103;

import java.net.ServerSocket;

public class DefaultContext
{
    private AbstractView defaultWindow;
    private ServerSocket defaultSSocket;

    // Eager singleton initializer
    private static final DefaultContext CONTEXT = new DefaultContext();

    // Eager singleton constructor
    private DefaultContext() {}

    // Eager singleton context getter
    public static DefaultContext getContext() { return CONTEXT; }

    public AbstractView getDefaultWindow() { return defaultWindow; }

    public ServerSocket getDefaultSSocket() { return defaultSSocket; }

    public void setDefaultWindow(AbstractView defaultWindow)
    {
        this.defaultWindow = defaultWindow;
    }

    public void setDefaultSSocket(ServerSocket defaultSSocket)
    {
        this.defaultSSocket = defaultSSocket;
    }
}
