package br.usp.icmc.ssc0103;

// Encapsulate everything no matter what
public interface AbstractTableEntry
{
    String serialize();

    String[] tokenize();
}

class CustomerEntry implements AbstractTableEntry
{
    @Override
    public String serialize()
    {
        return null;
    }

    @Override
    public String[] tokenize()
    {
        return new String[0];
    }
}

class ProductEntry implements AbstractTableEntry
{
    private String name;
    private double cost;
    private String provider;
    private long   expireDate;
    private int    amountInStock;

    public ProductEntry(String name,
                        double cost,
                        String provider,
                        long expireDate,
                        int amountInStock)
    {
        this.name = name;
        this.cost = cost;
        this.provider = provider;
        this.expireDate = expireDate;
        this.amountInStock = amountInStock;
    }

    public String getName() { return name; }

    public double getCost() { return cost; }

    public String getProvider() { return provider; }

    public long getExpireDate() { return expireDate; }

    public int getAmountInStock() { return amountInStock; }

    public void setAmountInStock(int amountInStock) { this.amountInStock = amountInStock; }

    @Override
    public String serialize()
    {
        return name
               + "," + cost
               + "," + provider
               + "," + expireDate
               + "," + amountInStock;
    }

    @Override
    public String[] tokenize()
    {
        return serialize().split(",");
    }
}
