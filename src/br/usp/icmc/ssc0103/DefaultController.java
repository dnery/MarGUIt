package br.usp.icmc.ssc0103;

import com.googlecode.lanterna.gui.component.ActionListBox;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.dialog.MessageBox;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class DefaultController
{
    // Eager singleton initializer
    private static final DefaultController CONTROLLER = new DefaultController();

    // Eager singleton constructor
    private DefaultController() {}

    // Eager singleton context getter
    public static DefaultController getController() { return CONTROLLER;}

    public void engageManagementControls() throws UnknownHostException
    {
        // Contextualize
        AbstractView defaultWindow = DefaultContext.getContext().getDefaultWindow();
        ServerSocket defaultSSocket = DefaultContext.getContext().getDefaultSSocket();

        // Define panes
        Panel topLayout = new Panel(Panel.Orientation.HORISONTAL);
        Panel botLayout = new Panel(Panel.Orientation.HORISONTAL);
        Panel primaryControls = new Panel("Main options", Panel.Orientation.VERTICAL);
        Panel secondaryControls = new Panel("List viewing", Panel.Orientation.VERTICAL);
        Panel infoDisplayPanel = new Panel("Info display", Panel.Orientation.VERTICAL);
        Panel serverInfoPanel = new Panel("Listening on", Panel.Orientation.VERTICAL);

        // Primary pane elements
        primaryControls.addComponent(new Button("Insert Product", () -> {
            MessageBox.showMessageBox(defaultWindow.expose().getOwner(),
                                      "Insert new product",
                                      "Do it.");
        }));
        primaryControls.addComponent(new Button("Browse Stock", () -> {
            MessageBox.showMessageBox(defaultWindow.expose().getOwner(),
                                      "Browse stock products",
                                      "Do it.");
        }));
        primaryControls.addComponent(new Button("Manage Stock", () -> {
            MessageBox.showMessageBox(defaultWindow.expose().getOwner(),
                                      "Manage stock products",
                                      "Do it.");
        }));
        primaryControls.addComponent(new Button("Quit", defaultWindow.expose()::close));

        // Secondary pane elements
        ActionListBox listBox = new ActionListBox();
        listBox.addAction("Action 1", () -> {
            MessageBox.showMessageBox(defaultWindow.expose() .getOwner(), "Action 1", "Do it.");
        });
        listBox.addAction("Action 2", () -> {
            MessageBox.showMessageBox(defaultWindow.expose() .getOwner(), "Action 2", "Do it.");
        });
        listBox.addAction("Action 3", () -> {
            MessageBox.showMessageBox(defaultWindow.expose() .getOwner(), "Action 3", "Do it.");
        });
        secondaryControls.addComponent(listBox);

        // Info display pane elements


        // Server info pane elements
        serverInfoPanel.addComponent(new Label("INet Hostname: " +
                                               InetAddress.getLocalHost().getCanonicalHostName() +
                                               "\nINet Address: " +
                                               InetAddress.getLocalHost().getHostAddress() +
                                               "\nRandom port: " +
                                               defaultSSocket.getLocalPort()));

        // Assemble final layout
        topLayout.addComponent(primaryControls);
        topLayout.addComponent(secondaryControls);
        topLayout.addComponent(infoDisplayPanel);
        botLayout.addComponent(serverInfoPanel);
        defaultWindow.expose().addComponent(topLayout);
        defaultWindow.expose().addComponent(botLayout);
    }
}
