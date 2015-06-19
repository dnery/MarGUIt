package br.usp.icmc.ssc0103;

import com.googlecode.lanterna.gui.GUIScreen;

import java.net.ServerSocket;

public class DefaultContext
{
    private GUIScreen    defaultParent;
    private ServerSocket defaultSSocket;

    // Eager singleton initializer
    private static final DefaultContext CONTEXT = new DefaultContext();

    // Eager singleton constructor
    private DefaultContext() {}

    // Eager singleton context getter
    public static DefaultContext getContext() { return CONTEXT; }

    public GUIScreen getDefaultParent() { return defaultParent; }

    public ServerSocket getDefaultSSocket() { return defaultSSocket; }

    public void setDefaultParent(GUIScreen defaultParent)
    {
        this.defaultParent = defaultParent;
    }

    public void setDefaultSSocket(ServerSocket defaultSSocket)
    {
        this.defaultSSocket = defaultSSocket;
    }
}
