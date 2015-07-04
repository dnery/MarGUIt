package br.usp.icmc.ssc0103.net;

import javafx.concurrent.Task;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.Scanner;

import static br.usp.icmc.ssc0103.net.ServerContext.getContext;

// This is the server socket listening service
public class ServerListener extends Task<Void>
{
    private ServerSocket serverSocket;

    public ServerListener(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;

        getContext().debug("Booted...");
    }

    @Override
    protected Void call()
    {
        while (true) {
            try {
                Socket socket = serverSocket.accept();

                getContext().debug("Captured...");

                // Creates the communcation loop task
                Thread loop = new Thread(new Connection(socket));
                loop.setDaemon(true);
                loop.start();
            } catch (IOException e) {
                getContext().dedoh(e.getMessage());
            }
        }
    }

    // This is the communication loop task
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
            try {
                Scanner reader = new Scanner(socket.getInputStream());

                getContext().debug("Enqueued...");

                while (reader.hasNextLine()) {
                    SMessage message = new SMessage(reader.nextLine());
                    getContext().debug("interpreting: " + message.get());
                    message.interpret(socket.getInputStream(),
                                      socket.getOutputStream());
                }
            } catch (IOException | ParseException e) {
                getContext().dedoh(e.getMessage());
            }
            return null;
        }
    }
}
