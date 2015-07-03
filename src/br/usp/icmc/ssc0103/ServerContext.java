package br.usp.icmc.ssc0103;

import java.io.IOException;

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

    private Listener listener;

    // Static block singleton constructor
    private ServerContext() {}

    // Static block singleton context getter
    public static ServerContext getContext() { return CONTEXT; }

    private void contextInit() throws IOException
    {
        listener = new Listener();
        listener.start();
    }

    public void debug(String message)
    {
        System.out.println(message);
    }

    public Listener getListener() { return listener; }
}
