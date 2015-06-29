package br.usp.icmc.ssc0103;

import java.net.ServerSocket;

public class DefaultContext
{
    // Eager singleton initializer
    private static final DefaultContext CONTEXT = new DefaultContext();

    private ServerSocket defaultSSocket;

    // Eager singleton constructor
    private DefaultContext() {}

    // Eager singleton context getter
    public static DefaultContext getContext() { return CONTEXT; }

    public ServerSocket getDefaultSSocket() { return defaultSSocket; }

    public void setDefaultSSocket(ServerSocket defaultSSocket)
    {
        this.defaultSSocket = defaultSSocket;
    }
}
