package br.usp.icmc.ssc0103.Models;

import br.usp.icmc.ssc0103.AbstractResources.AbstractTableEntry;

public class ReportEntry implements AbstractTableEntry
{
    private String customerName;
    private String productName;
    private int    amountSold;
    private long   saleDate;

    public ReportEntry(String customerName,
                       String productName,
                       int amountSold,
                       long saleDate)
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