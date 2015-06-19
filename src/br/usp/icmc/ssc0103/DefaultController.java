package br.usp.icmc.ssc0103;

import com.googlecode.lanterna.gui.Container;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.ActionListBox;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Map;

public class DefaultController
{
    // Eager singleton initializer
    private static final DefaultController CONTROLLER = new DefaultController();

    // Eager singleton constructor
    private DefaultController() {}

    // Eager singleton context getter
    public static DefaultController getController() { return CONTROLLER;}

    public void managementControllers(Window window, Map<String, Container> context)
    {
        // Contextualize
        ServerSocket defaultSSocket = DefaultContext.getContext().getDefaultSSocket();

        // Primary pane elements
        context.get("controlPane1").addComponent(new Button("Insert Product", () -> {
            (new RegisterNewProductPrompt("Insert new product")).display();
        }));
        context.get("controlPane1").addComponent(new Button("Browse Stock", () -> {
            MessageBox.showMessageBox(window.getOwner(), "Browse stock products", "Do it.");
        }));
        context.get("controlPane1").addComponent(new Button("Manage Stock", () -> {
            MessageBox.showMessageBox(window.getOwner(), "Manage stock products", "Do it.");
        }));
        context.get("controlPane1").addComponent(new Button("Dish Reports", () -> {
            MessageBox.showMessageBox(window.getOwner(), "Output sales reports", "Do it.");
        }));
        context.get("controlPane1").addComponent(new Button("Quit", window::close));

        // Secondary pane elements
        ActionListBox listBox = new ActionListBox();
        listBox.addAction("Action 1", () -> {
            MessageBox.showMessageBox(window.getOwner(), "Action 1", "Do it.");
        });
        listBox.addAction("Action 2", () -> {
            MessageBox.showMessageBox(window.getOwner(), "Action 2", "Do it.");
        });
        listBox.addAction("Action 3", () -> {
            MessageBox.showMessageBox(window.getOwner(), "Action 3", "Do it.");
        });
        context.get("controlPane2").addComponent(listBox);

        // Info display pane elements


        // Server info pane elements
        try {
            context.get("infoPane2").addComponent(new Label(
                    "INet Hostname: " +
                    InetAddress.getLocalHost().getCanonicalHostName() +
                    "\nINet Address: " +
                    InetAddress.getLocalHost().getHostAddress() +
                    "\nRandom port: " +
                    defaultSSocket.getLocalPort()
            ));
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        }
    }

    public void registerNewProductControllers(Window window, Map<String, Container> context)
    {
        // Primary pane elements
        context.get("controlPane1").addComponent(new Label("Name", true));
        context.get("controlPane1").addComponent(new Label("Cost", true));
        context.get("controlPane1").addComponent(new Label("Provider", true));
        context.get("controlPane1").addComponent(new Label("Expiration (MM/dd/yyyy)", true));
        context.get("controlPane1").addComponent(new Label("Initial amount in stock", true));

        // Secondary pane elements
        TextBox[] textBoxes = new TextBox[5];
        for (int i = 0; i < textBoxes.length; i++)
            context.get("controlPane2").addComponent(textBoxes[i] = new TextBox());

        // Define collection behaviour
        context.get("botLayout").addComponent(new Button("Done", () -> {
            try {
                DefaultDatabase.getInstance().newProduct(textBoxes[0].getText(),
                                                         textBoxes[1].getText(),
                                                         textBoxes[2].getText(),
                                                         textBoxes[3].getText(),
                                                         textBoxes[4].getText());
            } catch (ParseException e) {
                MessageBox.showMessageBox(window.getOwner(), "Oops!", "Parsing error...");
            }
            window.close();
        }));
    }
}
