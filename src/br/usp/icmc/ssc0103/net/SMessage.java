package br.usp.icmc.ssc0103.net;

import br.usp.icmc.ssc0103.model.ProductEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;

import static br.usp.icmc.ssc0103.DefaultDatabase.getInstance;
import static br.usp.icmc.ssc0103.net.ServerContext.getContext;

// Server context message object
public class SMessage implements Serializable
{
    private String message;

    public SMessage(String message) { this.message = message; }

    // Simple vector structure based interpreter
    public Object interpret(InputStream is, OutputStream os) throws
                                                             ParseException
    {
        String[] syntaxTree = message.split(",");

        switch (syntaxTree[0]) {

            case "login":
                return null;

            case "adduser":
                getContext().debug("adduser");

                getInstance().newCustomer(syntaxTree[1],
                                          syntaxTree[2],
                                          syntaxTree[3],
                                          syntaxTree[4],
                                          syntaxTree[5],
                                          syntaxTree[6]);

                return null;

            case "getproducts":
                getContext().debug("getproducts");

                PrintWriter pw = new PrintWriter(os, true);
                ObservableList<ProductEntry> entries = FXCollections
                        .observableArrayList
                                (getInstance().getObservableProducts());

                pw.println("getproducts");

                for (ProductEntry entry : entries) {
                    String line = entry.serialize();
                    getContext().debug(line);
                    pw.println(line);
                }
                pw.println("OVER");

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
