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

    public void setAmountInStock(int amountInStock) { this.amountInStock = amountInStock; }

    @Override
    public String serialize()
    {
        return name + "," + cost + "," + provider + "," + expireDate + "," + amountInStock;
    }

    @Override
    public String[] tokenize() { return serialize().split(","); }
}

class ReportEntry implements AbstractTableEntry
{
    private String customerName;
    private String productName;
    private int    amountSold;
    private long   saleDate;

    public ReportEntry(String customerName, String productName, int amountSold, long saleDate)
    {
        this.customerName = customerName;
        this.productName = productName;
        this.amountSold = amountSold;
        this.saleDate = saleDate;
    }

    public ReportEntry(String[] tokens)
    {
        customerName = tokens[0];
        productName = tokens[1];
        amountSold = Integer.parseInt(tokens[2]);
        saleDate = Long.parseLong(tokens[3]);
    }

    public String getCustomerName() { return customerName; }

    public String getProductName() { return productName; }

    public int getAmountSold() { return amountSold; }

    public long getSaleDate() { return saleDate; }

    @Override
    public String serialize()
    {
        return customerName + "," + productName + "," + amountSold + "," + saleDate;
    }

    @Override
    public String[] tokenize() { return serialize().split(","); }
}
