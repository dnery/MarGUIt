package br.usp.icmc.ssc0103;

import com.googlecode.lanterna.gui.Container;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Panel;

import java.util.HashMap;
import java.util.Map;

// Encapsulate everything no matter what
public interface AbstractView
{
    void display();
}

class CustomerView implements AbstractView
{
    @Override
    public void display()
    {

    }
}

class ManagementView implements AbstractView
{
    private Window window;

    private Map<String, Container> layout;

    public ManagementView(String title)
    {
        // Initialize attributes
        window = new Window(title);
        layout = new HashMap<>();

        // Define initial containers
        layout.put("topLayout", new Panel(Panel.Orientation.HORISONTAL));
        layout.put("botLayout", new Panel(Panel.Orientation.HORISONTAL));
        layout.put("controlPane1", new Panel("Control options", Panel.Orientation.VERTICAL));
        layout.put("controlPane2", new Panel("Listing options", Panel.Orientation.VERTICAL));
        layout.put("infoPane1", new Panel("Database info", Panel.Orientation.VERTICAL));
        layout.put("infoPane2", new Panel("Listener info", Panel.Orientation.VERTICAL));

        // Compose initial containers
        layout.get("topLayout").addComponent(layout.get("controlPane1"));
        layout.get("topLayout").addComponent(layout.get("controlPane2"));
        layout.get("topLayout").addComponent(layout.get("infoPane1"));
        layout.get("botLayout").addComponent(layout.get("infoPane2"));

        // Populate initial containers
        DefaultController.getController().managementControllers(window, layout);
    }

    @Override
    public void display()
    {
        // Assemble final layout containers
        window.addComponent(layout.get("topLayout"));
        window.addComponent(layout.get("botLayout"));
        DefaultContext.getContext()
                      .getDefaultParent()
                      .showWindow(window, GUIScreen.Position.CENTER);
    }
}

class RegisterNewCustomerPrompt implements AbstractView
{
    @Override
    public void display()
    {

    }
}

class RegisterNewProductPrompt implements AbstractView
{
    private Window window;

    private Map<String, Container> layout;

    public RegisterNewProductPrompt(String title)
    {
        window = new Window(title);
        layout = new HashMap<>();

        layout.put("topLayout", new Panel(Panel.Orientation.HORISONTAL));
        layout.put("botLayout", new Panel(Panel.Orientation.VERTICAL));
        layout.put("controlPane1", new Panel(Panel.Orientation.VERTICAL));
        layout.put("controlPane2", new Panel(Panel.Orientation.VERTICAL));

        layout.get("topLayout").addComponent(layout.get("controlPane1"));
        layout.get("topLayout").addComponent(layout.get("controlPane2"));

        DefaultController.getController().registerNewProductControllers(window, layout);
    }

    @Override
    public void display()
    {
        window.addComponent(layout.get("topLayout"));
        window.addComponent(layout.get("botLayout"));
        DefaultContext.getContext()
                      .getDefaultParent()
                      .showWindow(window, GUIScreen.Position.CENTER);
    }
}
