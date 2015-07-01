package br.usp.icmc.ssc0103.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class ProductEntry implements AbstractTableEntry, Serializable
{
    private final SimpleStringProperty  name;
    private final SimpleDoubleProperty  cost;
    private final SimpleStringProperty  provider;
    private final SimpleLongProperty    expireDate;
    private final SimpleIntegerProperty amountInStock;

    public ProductEntry(Builder builder)
    {
        name = builder.nestedName;
        cost = builder.nestedCost;
        provider = builder.nestedProvider;
        expireDate = builder.nestedExpireDate;
        amountInStock = builder.nestedAmountInStock;
    }

    public String getName()
    {
        return name.get();
    }

    public SimpleStringProperty nameProperty()
    {
        return name;
    }

    public double getCost()
    {
        return cost.get();
    }

    public SimpleDoubleProperty costProperty()
    {
        return cost;
    }

    public String getProvider()
    {
        return provider.get();
    }

    public SimpleStringProperty providerProperty()
    {
        return provider;
    }

    public long getExpireDate()
    {
        return expireDate.get();
    }

    public SimpleLongProperty expireDateProperty()
    {
        return expireDate;
    }

    public int getAmountInStock()
    {
        return amountInStock.get();
    }

    public SimpleIntegerProperty amountInStockProperty()
    {
        return amountInStock;
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public void setCost(double cost)
    {
        this.cost.set(cost);
    }

    public void setProvider(String provider)
    {
        this.provider.set(provider);
    }

    public void setExpireDate(long expireDate)
    {
        this.expireDate.set(expireDate);
    }

    public void setAmountInStock(int amountInStock)
    {
        this.amountInStock.set(amountInStock);
    }

    @Override
    public String serialize()
    {
        return name.get() +
               "," + cost.get() +
               "," + provider.get() +
               "," + expireDate.get() +
               "," + amountInStock.get();
    }

    // Not too readable builder
    public static class Builder
    {
        private final SimpleStringProperty  nestedName;
        private final SimpleDoubleProperty  nestedCost;
        private final SimpleStringProperty  nestedProvider;
        private final SimpleLongProperty    nestedExpireDate;
        private final SimpleIntegerProperty nestedAmountInStock;

        public Builder()
        {
            nestedName = new SimpleStringProperty();
            nestedCost = new SimpleDoubleProperty();
            nestedProvider = new SimpleStringProperty();
            nestedExpireDate = new SimpleLongProperty();
            nestedAmountInStock = new SimpleIntegerProperty();
        }

        public Builder name(String name)
        {
            nestedName.set(name);
            return this;
        }

        public Builder cost(String cost)
        {
            double value = Double.parseDouble(cost);
            nestedCost.set(value);
            return this;
        }

        public Builder provider(String provider)
        {
            nestedProvider.set(provider);
            return this;
        }

        public Builder expireDate(String expireDate)
        {
            long value = Long.parseLong(expireDate);
            nestedExpireDate.set(value);
            return this;
        }

        public Builder amountInStock(String amountInStock)
        {
            int value = Integer.parseInt(amountInStock);
            nestedAmountInStock.set(value);
            return this;
        }

        public ProductEntry build()
        {
            return new ProductEntry(this);
        }
    }
}
