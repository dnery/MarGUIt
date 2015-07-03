package br.usp.icmc.ssc0103;

import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Service<Void>
{
    private ServerSocket         serverSocket;
    private SimpleStringProperty input;
    private SimpleStringProperty reply;

    public Listener()
    {
        try {
            serverSocket = new ServerSocket(0);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected Task<Void> createTask()
    {
        return new Task<Void>()
        {
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
        };
    }

    public ServerSocket getServerSocket() { return serverSocket; }

    public String getInput() { return input.get(); }

    public SimpleStringProperty inputProperty() { return input; }

    public String getReply() { return reply.get(); }

    public SimpleStringProperty replyProperty() { return reply; }

    public class Connection extends Task<Void>
    {
        private Socket             socket;
        private ObjectInputStream  inputStream;
        private ObjectOutputStream outputStream;

        public Connection(Socket socket)
        {
            this.socket = socket;

            try {
                inputStream = new ObjectInputStream(socket.getInputStream());
                outputStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        @Override
        protected Void call()
        {
            Message message;
            while (true) {
                try {
                    if ((message = (Message) inputStream.readObject()) != null)
                        outputStream.writeObject(message.interpret());
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println(e.getMessage());
                    try {
                        socket.close();
                    } catch (IOException f) {
                        System.err.println(f.getMessage());
                    }
                }
            }
        }
    }
}
