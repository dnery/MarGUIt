package br.usp.icmc.ssc0103;

import javafx.concurrent.Task;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientContext
{
    // Static block singleton initializer
    private static ClientContext CONTEXT;

    static {
        CONTEXT = new ClientContext();
        CONTEXT.contextInit();
    }

    // Attributes
    private Peer    peer;
    private Message input;
    private Message reply;

    private ObjectInputStream  inputStream;
    private ObjectOutputStream outputStream;

    // Static block singleton constructor
    private ClientContext() {}

    // Static block singleton context getter
    public static ClientContext getContext() { return CONTEXT; }

    private void contextInit() { }

    public void connect(String addr, String port)
    {
        peer = new Peer(addr, port);
        peer.setOnSucceeded(event -> {
            debug("Successfully connected to remote server!");
        });
        peer.setOnFailed(event -> {
            debug(event.getSource().getException().getMessage());
        });
        Thread oneShot = new Thread(peer);
        oneShot.start();
    }

    public void debug(String message)
    {
        System.out.println(message);
    }

    public Peer getPeer()
    {
        return peer;
    }

    public void setPeer(Peer peer)
    {
        this.peer = peer;
    }

    public Message getInput()
    {
        return input;
    }

    public void setInput(Message input)
    {
        this.input = input;
    }

    public Message getReply()
    {
        return reply;
    }

    public void setReply(Message reply)
    {
        this.reply = reply;
    }

    public void remoteRead()
    {
        FireRead read = new FireRead();
        read.setOnSucceeded(event -> {
            reply = (Message) event.getSource().getValue();
        });
        read.setOnFailed(event -> {
            debug(event.getSource().getException().getMessage());
        });
        Thread oneShot = new Thread(read);
        oneShot.start();
    }

    public void remoteWrite()
    {
        FireWrite write = new FireWrite();
        write.setOnSucceeded(event -> {
            debug("Object successfully written on away stream!");
        });
        write.setOnFailed(event -> {
            debug(event.getSource().getException().getMessage());
        });
        Thread oneShot = new Thread(write);
        oneShot.start();
    }

    public class Peer extends Task<Void>
    {
        private String addr;
        private String port;

        public Peer(String addr, String port)
        {
            this.addr = addr;
            this.port = port;
        }

        @Override
        protected Void call() throws Exception
        {
            Socket socket = new Socket(addr, Integer.parseInt(port));
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            return null;
        }
    }

    public class FireWrite extends Task<Void>
    {
        @Override
        protected Void call() throws Exception
        {
            outputStream.writeObject(input);

            return null;
        }
    }

    public class FireRead extends Task<Message>
    {
        @Override
        protected Message call() throws Exception
        {
            return (Message) inputStream.readObject();
        }
    }
}
