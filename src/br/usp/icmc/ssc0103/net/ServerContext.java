package br.usp.icmc.ssc0103.net;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerContext
{
    // Static block singleton initializer
    private static ServerContext CONTEXT;

    static {
        try {
            CONTEXT = new ServerContext();
            CONTEXT.contextInit();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // Attributes
    private ServerSocket serverSocket;

    // Static block singleton constructor
    private ServerContext() {}

    // Static block singleton context getter
    public static ServerContext getContext() { return CONTEXT; }

    // Static block singleton work-around init
    private void contextInit() throws IOException
    {
        serverSocket = new ServerSocket(0);

        Thread loop = new Thread(new ServerListener(serverSocket));
        loop.setDaemon(true);
        loop.start();
    }

    // Print debug message
    public void debug(String message)
    {
        System.out.println(message);
    }

    // Print error message
    public void dedoh(String message)
    {
        System.err.println(message);
    }

    public ServerSocket getServerSocket() { return serverSocket; }
}
