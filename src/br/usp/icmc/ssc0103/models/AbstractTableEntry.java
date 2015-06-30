package br.usp.icmc.ssc0103.models;

// Encapsulate everything no matter what
public interface AbstractTableEntry
{
    String serialize();

    String[] tokenize();
}

