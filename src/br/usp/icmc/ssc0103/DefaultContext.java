package br.usp.icmc.ssc0103;

import java.io.IOException;
import java.net.ServerSocket;

public class DefaultContext
{
    // Static block singleton initializer
    private static DefaultContext CONTEXT;

    static {
        try {
            CONTEXT = new DefaultContext();
            CONTEXT.contextInit();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private ServerSocket serverSocket;

    // Static block singleton constructor
    private DefaultContext() {}

    // Static block singleton context getter
    public static DefaultContext getContext() { return CONTEXT; }

    private void contextInit() throws IOException
    {
        serverSocket = new ServerSocket(0);

        Thread listener = new Thread(new Listener(serverSocket));
        listener.setDaemon(true);
        listener.start();
    }

    public ServerSocket getServerSocket() { return serverSocket; }
}
