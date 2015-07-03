package br.usp.icmc.ssc0103;

// Custom exception

import java.io.Serializable;

public class Message implements Serializable
{
    String prefix;
    Object suffix;

    public Message(String prefix)
    {
        this.prefix = prefix;
    }

    public Message(String prefix, Object suffix)
    {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public Object interpret()
    {
        switch (prefix) {

            case "login":
                return null;

            case "adduser":
                ServerContext.getContext().debug("adduser");
                return null;

            case "getproducts":
                ServerContext.getContext().debug("getproducts");
                return null;

            case "makepurchase":
                return null;

            case "setnotify":
                return null;

            default:
                return null;
        }
    }

    public String getPrefix()
    {
        return prefix;
    }

    public Object getSuffix()
    {
        return suffix;
    }
}
