package br.usp.icmc.ssc0103;

import br.usp.icmc.ssc0103.models.AbstractTableEntry;
import br.usp.icmc.ssc0103.models.CustomerEntry;
import br.usp.icmc.ssc0103.models.ProductEntry;
import br.usp.icmc.ssc0103.models.ReportEntry;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DefaultDatabase
{
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

    private final File defaultDatabaseRoot = new File("database");
    private final File customersFile       = new File(defaultDatabaseRoot +
                                                      "/customers.csv");
    private final File productsFile        = new File(defaultDatabaseRoot +
                                                      "/products.csv");
    private final File reportsFile         = new File(defaultDatabaseRoot +
                                                      "/reports.csv");
    private List<CustomerEntry>           customers;
    private List<ProductEntry>            products;
    private List<ReportEntry>             reports;
    private ObservableList<CustomerEntry> observableCustomers;
    private ObservableList<ProductEntry>  observableProducts;
    private ObservableList<ReportEntry>   observableReports;

    // Static block singleton constructor
    private DefaultDatabase() { }

    // Static block singleton context getter
    public static DefaultDatabase getInstance() { return INSTANCE; }

    // Static block singleton auxiliary initiator
    private void databaseInit() throws IOException
    {
        // Start data structures
        customers = new ArrayList<>();
        products = new ArrayList<>();
        reports = new ArrayList<>();

        // Load paths and files
        defaultDatabaseRoot.mkdir();
        customersFile.createNewFile();
        productsFile.createNewFile();
        reportsFile.createNewFile();

        // Read from files
        readAndPopulate();

        // Customers list: write on event
        observableCustomers = FXCollections.observableList(customers);
        observableCustomers.addListener(
                (ListChangeListener<AbstractTableEntry>) c -> {
                    try {
                        serializeAndWrite(customersFile, customers);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });

        // Products list: write on event
        observableProducts = FXCollections.observableList(products);
        observableProducts.addListener(
                (ListChangeListener<AbstractTableEntry>) c -> {
                    try {
                        serializeAndWrite(productsFile, products);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });

        // Reports list: write on event
        observableReports = FXCollections.observableList(reports);
        observableReports.addListener(
                (ListChangeListener<AbstractTableEntry>) c -> {
                    try {
                        serializeAndWrite(reportsFile, reports);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });
    }

    public void newProduct(String... arguments) throws ParseException
    {
        if (products.stream().noneMatch(product -> product.getName().equals(
                arguments[0]))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            // Add to observable list
            observableProducts.add(
                    new ProductEntry(arguments[0],
                                     Double.parseDouble(arguments[1]),
                                     arguments[2],
                                     dateFormat.parse(arguments[3]).getTime(),
                                     Integer.parseInt(arguments[4])));

            // Add to regular list
            products.add(
                    new ProductEntry(arguments[0],
                                     Double.parseDouble(arguments[1]),
                                     arguments[2],
                                     dateFormat.parse(arguments[3]).getTime(),
                                     Integer.parseInt(arguments[4])));
        }
    }

    public void readAndPopulate() throws IOException
    {
        // Reader shenanigans
        BufferedReader fileReader;
        String bufferedLine;

        // Read from customer entries file
        fileReader = new BufferedReader(new FileReader(customersFile));
        while ((bufferedLine = fileReader.readLine()) != null)
            customers.add(new CustomerEntry(bufferedLine.split(",")));
        fileReader.close();

        // Read from product entries file
        fileReader = new BufferedReader(new FileReader(productsFile));
        while ((bufferedLine = fileReader.readLine()) != null)
            products.add(new ProductEntry(bufferedLine.split(",")));
        fileReader.close();

        // Read from report entries file
        fileReader = new BufferedReader(new FileReader(reportsFile));
        while ((bufferedLine = fileReader.readLine()) != null)
            reports.add(new ReportEntry(bufferedLine.split(",")));
        fileReader.close();
    }

    public void serializeAndWrite(File filePointer,
                                  List<? extends AbstractTableEntry>
                                          bufferedList) throws IOException
    {
        // Write to generic entries file
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(
                filePointer, false));
        for (AbstractTableEntry abstractTableEntry : bufferedList)
            fileWriter.write(abstractTableEntry.serialize() + "\n");
        fileWriter.close();
    }
}
