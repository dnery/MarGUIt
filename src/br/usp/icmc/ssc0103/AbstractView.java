package br.usp.icmc.ssc0103;

import com.googlecode.lanterna.gui.Window;

// Encapsulate everything no matter what
public interface AbstractView
{
    Window expose();
}

class CustomerView extends Window implements AbstractView
{
    public CustomerView(String title) { super(title); }

    @Override
    public Window expose() { return this; }
}

class ManagementView extends Window implements AbstractView
{
    public ManagementView(String title) { super(title); }

    @Override
    public Window expose() { return this; }
}
