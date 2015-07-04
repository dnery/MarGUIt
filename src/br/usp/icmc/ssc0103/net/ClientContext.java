package br.usp.icmc.ssc0103.net;

import javafx.concurrent.Task;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientContext
{
    // Static block singleton initializer
    private static ClientContext CONTEXT;

    static {
        CONTEXT = new ClientContext();
    }

    // Attributes
    private Peer         peer;
    private SMessage     input;
    private InputStream  inputStream;
    private OutputStream outputStream;

    // Static block singleton constructor
    private ClientContext() {}

    // Static block singleton context getter
    public static ClientContext getContext() { return CONTEXT; }

    public void connect(String addr, String port)
    {
        peer = new Peer(addr, port);
        peer.setOnSucceeded(event -> debug("Connection cut"));
        peer.setOnFailed(event -> dedoh(peer.getException().getMessage()));
        Thread oneshot = new Thread(peer);
        oneshot.start();
    }

    public SMessage getInput()
    {
        return input;
    }

    public void setInput(SMessage input)
    {
        this.input = input;
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

    // Creates the write task
    public void remoteWrite()
    {
        FireWrite write = new FireWrite(outputStream, input);
        write.setOnSucceeded(event -> debug("Write successful!"));
        write.setOnFailed(event -> dedoh(write.getException().getMessage()));

        Thread oneShot = new Thread(write);
        oneShot.start();
    }

    // Client connection task
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

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            debug("Plugged...");

            Scanner reader = new Scanner(socket.getInputStream());
            while (reader.hasNextLine()) {
                CMessage message = new CMessage(reader.nextLine());
                debug("interpreting: " + message.get());
                message.interpret(socket.getInputStream(),
                                  socket.getOutputStream());
            }
            return null;
        }
    }

    // Client socket "one-shot" write task
    public class FireWrite extends Task<Void>
    {
        OutputStream stream;
        SMessage     message;

        public FireWrite(OutputStream stream, SMessage message)
        {
            this.stream = stream;
            this.message = message;
        }

        @Override
        protected Void call() throws Exception
        {
            PrintWriter writer = new PrintWriter(stream, true);
            writer.println(message.get());
            debug(message.get());
            return null;
        }
    }
}
