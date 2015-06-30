package br.usp.icmc.ssc0103.models;

public class ProductEntry implements AbstractTableEntry
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

    public ProductEntry(String[] tokens)
    {
        name = tokens[0];
        cost = Double.parseDouble(tokens[1]);
        provider = tokens[2];
        expireDate = Long.parseLong(tokens[3]);
        amountInStock = Integer.parseInt(tokens[4]);
    }

    public String getName() { return name; }

    public double getCost() { return cost; }

    public String getProvider() { return provider; }

    public long getExpireDate() { return expireDate; }

    public int getAmountInStock() { return amountInStock; }

    public void setName(String name) { this.name = name; }

    public void setCost(double cost) { this.cost = cost; }

    public void setProvider(String provider) { this.provider = provider; }

    public void setExpireDate(long expireDate)
    {
        this.expireDate = expireDate;
    }

    public void setAmountInStock(int amountInStock)
    {
        this.amountInStock = amountInStock;
    }

    @Override
    public String serialize()
    {
        return name +
               "," + cost +
               "," + provider +
               "," + expireDate +
               "," + amountInStock;
    }

    @Override
    public String[] tokenize() { return serialize().split(","); }
}
