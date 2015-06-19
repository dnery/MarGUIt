package br.usp.icmc.ssc0103;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultDatabase
{
    private List<AbstractTableEntry> customers;
    private List<AbstractTableEntry> products;
    private List<AbstractTableEntry> reports;

    private ObservableList<AbstractTableEntry> observableCustomers;
    private ObservableList<AbstractTableEntry> observableProducts;
    private ObservableList<AbstractTableEntry> observableReports;

    private final File databasesRoot = new File("database");
    private final File customersFile = new File(databasesRoot + "/customers.csv");
    private final File productsFile  = new File(databasesRoot + "/products.csv");
    private final File reportsFile   = new File(databasesRoot + "/reports.csv");

    // Static block singleton initiator
    private static DefaultDatabase INSTANCE;

    static {
        try {
            INSTANCE = new DefaultDatabase();
            INSTANCE.databaseInit();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // Static block singleton constructor
    private DefaultDatabase() { }

    // Static block singleton context getter
    public static DefaultDatabase getInstance() { return INSTANCE; }

    // Static block singleton auxiliary initiator
    private void databaseInit() throws IOException
    {
        customers = new ArrayList<>();
        products = new ArrayList<>();
        reports = new ArrayList<>();

        databasesRoot.mkdir();
        customersFile.createNewFile();
        productsFile.createNewFile();
        reportsFile.createNewFile();

        loadBuffers();

        //Observables
    }

    private void loadBuffers() throws FileNotFoundException, IOException
    {
        BufferedReader fileReader;
        String bufferedLine;

        // Customers file

        // Products file
        fileReader = new BufferedReader(new FileReader(productsFile));
        while ((bufferedLine = fileReader.readLine()) != null) {
            products.add(new ProductEntry(bufferedLine.split(",")));
        }
        fileReader.close();

        // Reports file
        fileReader = new BufferedReader(new FileReader(reportsFile));
        while ((bufferedLine = fileReader.readLine()) != null) {
            reports.add(new ReportEntry(bufferedLine.split(",")));
        }
        fileReader.close();
    }
}
