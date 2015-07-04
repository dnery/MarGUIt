package br.usp.icmc.ssc0103.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static br.usp.icmc.ssc0103.DefaultDatabase.getInstance;
import static br.usp.icmc.ssc0103.net.ClientContext.getContext;

// Client context message object
public class CMessage implements Serializable
{
    private String message;

    public CMessage(String message) { this.message = message; }

    // Simple vector structure based interpreter
    public Object interpret(InputStream is, OutputStream os) throws
                                                             ParseException
    {
        String[] syntaxTree = message.split(",");

        switch (syntaxTree[0]) {
            case "login":
                return null;

            case "adduser":
                return null;

            case "getproducts":
                getContext().debug("getproducts");

                Scanner s = new Scanner(is);

                while (s.hasNextLine()) {
                    String line = s.nextLine();

                    if (line.equals("OVER")) {
                        return null;
                    } else {
                        getContext().debug(line);

                        String[] data = line.split(",");
                        SimpleDateFormat df2 = new SimpleDateFormat("MM/yyyy");

                        getInstance().newProduct(data[0],
                                                 data[1],
                                                 data[2],
                                                 df2.format(new Date(data[3])),
                                                 data[4]);
                    }
                }
                return null;

            case "makepurchase":
                return null;

            case "setnotify":
                return null;

            default:
                return null;
        }
    }

    public String get() { return message; }
}
