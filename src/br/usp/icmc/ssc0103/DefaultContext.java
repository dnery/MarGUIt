package br.usp.icmc.ssc0103;

import br.usp.icmc.ssc0103.models.CustomerEntry;
import javafx.concurrent.Task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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

    private ServerSocket      serverSocket;
    private ArrayList<Socket> connections;

    // Static block singleton constructor
    private DefaultContext() {}

    // Static block singleton context getter
    public static DefaultContext getContext() { return CONTEXT; }

    private void contextInit() throws IOException
    {
        serverSocket = new ServerSocket(0);
        connections = new ArrayList<>();

        Thread listener = new Thread(new Listener());
        listener.setDaemon(true);
        listener.start();
    }

    public ServerSocket getServerSocket() { return serverSocket; }

    public ArrayList<Socket> getConnections() { return connections; }

    public class Listener extends Task<Void>
    {
        @Override
        protected Void call()
        {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    connections.add(socket);

                    Thread connection = new Thread(new Connection(socket));
                    connection.setDaemon(true);
                    connection.start();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public class Connection extends Task<Void>
    {
        private ObjectInputStream stream;

        public Connection(Socket socket) throws IOException
        {
            stream = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        protected Void call()
        {
            while (true) {
                try {
                    CustomerEntry entry = (CustomerEntry) stream.readObject();

                    //DO STUFF!
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
