package br.usp.icmc.ssc0103;

public interface AbstractTableEntry
{
    String serialize();
}

class CustomerEntry implements AbstractTableEntry
{
    @Override
    public String serialize()
    {
        return null;
    }
}

class ProductEntry implements AbstractTableEntry
{
    @Override
    public String serialize()
    {
        return null;
    }
}
