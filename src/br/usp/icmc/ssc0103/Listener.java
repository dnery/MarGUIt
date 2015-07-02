package br.usp.icmc.ssc0103;

import javafx.concurrent.Task;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Task<Void>
{
    private ServerSocket serverSocket;

    public Listener(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }

    @Override
    protected Void call()
    {
        while (true) {
            try {
                Socket socket = serverSocket.accept();

                Thread connection = new Thread(new Connection(socket));
                connection.setDaemon(true);
                connection.start();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public class Connection extends Task<Void>
    {
        private Socket socket;

        public Connection(Socket socket)
        {
            this.socket = socket;
        }

        @Override
        protected Void call()
        {
            while (true) {
               //DO STUFF !
            }
        }
    }
}
