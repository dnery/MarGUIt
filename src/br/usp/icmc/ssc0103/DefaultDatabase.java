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

        // Load paths and files
        defaultDatabaseRoot.mkdir();
        customersFile.createNewFile();
        productsFile.createNewFile();
        reportsFile.createNewFile();

        // Customers list: write on event
        observableCustomers = FXCollections.observableArrayList();
        observableCustomers.addListener(
                (ListChangeListener<AbstractTableEntry>) c -> {
                    try {
                        serializeAndWrite(customersFile, observableCustomers);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });

        // Products list: write on event
        observableProducts = FXCollections.observableArrayList();
        observableProducts.addListener(
                (ListChangeListener<AbstractTableEntry>) c -> {
                    try {
                        serializeAndWrite(productsFile, observableProducts);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });

        // Reports list: write on event
        observableReports = FXCollections.observableArrayList();
        observableReports.addListener(
                (ListChangeListener<AbstractTableEntry>) c -> {
                    try {
                        serializeAndWrite(reportsFile, observableReports);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });

        // Read from files
        readAndPopulate();
    }

    public void newProduct(String... arguments) throws NumberFormatException,
                                                       NullPointerException,
                                                       ParseException

    {
        if (observableProducts.stream().noneMatch(product -> product
                                                          .getName()
                                                          .equals(arguments[0])
                                                 )) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            // Add to observable list
            observableProducts.add(
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
            observableCustomers.add(new CustomerEntry(bufferedLine.split(",")));
        fileReader.close();

        // Read from product entries file
        fileReader = new BufferedReader(new FileReader(productsFile));
        while ((bufferedLine = fileReader.readLine()) != null)
            observableProducts.add(new ProductEntry(bufferedLine.split(",")));
        fileReader.close();

        // Read from report entries file
        fileReader = new BufferedReader(new FileReader(reportsFile));
        while ((bufferedLine = fileReader.readLine()) != null)
            observableReports.add(new ReportEntry(bufferedLine.split(",")));
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

    public ObservableList<CustomerEntry> getObservableCustomers()
    {
        return observableCustomers;
    }

    public ObservableList<ProductEntry> getObservableProducts()
    {
        return observableProducts;
    }

    public ObservableList<ReportEntry> getObservableReports()
    {
        return observableReports;
    }
}
