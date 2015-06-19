package br.usp.icmc.ssc0103;

// Encapsulate everything no matter what
public interface AbstractTableEntry
{
    String serialize();

    String[] tokenize();
}

class CustomerEntry implements AbstractTableEntry
{
    private String name;
    private String address;
    private String phoneNumber;
    private String userEmail;
    private String userName;
    private String password;

    public CustomerEntry(String name,
                         String address,
                         String phoneNumber,
                         String userEmail,
                         String userName, String password)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
    }

    public CustomerEntry(String[] tokens)
    {
        name = tokens[0];
        address = tokens[1];
        phoneNumber = tokens[2];
        userEmail = tokens[3];
        userName = tokens[4];
        password = tokens[5];
    }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getUserEmail() { return userEmail; }

    public String getUserName() { return userName; }

    public String getPassword() { return password; }

    @Override
    public String serialize()
    {
        return name +
               "," + address +
               "," + phoneNumber +
               "," + userEmail +
               "," + userName +
               "," + password;
    }

    @Override
    public String[] tokenize() { return serialize().split(","); }
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
        return name +
               "," + cost +
               "," + provider +
               "," + expireDate +
               "," + amountInStock;
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
        return customerName +
               "," + productName +
               "," + amountSold +
               "," + saleDate;
    }

    @Override
    public String[] tokenize() { return serialize().split(","); }
}
