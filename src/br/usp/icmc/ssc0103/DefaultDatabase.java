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

    private final File databaseRoot  = new File("database");
    private final File customersFile = new File(databaseRoot +
                                                "/customers.csv");
    private final File productsFile  = new File(databaseRoot +
                                                "/products.csv");
    private final File reportsFile   = new File(databaseRoot +
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
        databaseRoot.mkdir();
        customersFile.createNewFile();
        productsFile.createNewFile();
        reportsFile.createNewFile();

        // Customers list: write on event
        observableCustomers = FXCollections.observableArrayList();
        observableCustomers.addListener(
                (ListChangeListener<AbstractTableEntry>) c -> {
                    observableCustomers.sort(
                            (customerEntry, t1) ->
                                    customerEntry
                                            .getName()
                                            .compareTo(t1.getName())
                                            );

                    try {
                        serializeAndWrite(customersFile, observableCustomers);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                });

        // Products list: write on event
        observableProducts = FXCollections.observableArrayList();
        observableProducts.addListener(
                (ListChangeListener<AbstractTableEntry>) c -> {
                    try {
                        serializeAndWrite(productsFile, observableProducts);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                });

        // Reports list: write on event
        observableReports = FXCollections.observableArrayList();
        observableReports.addListener(
                (ListChangeListener<AbstractTableEntry>) c -> {
                    try {
                        serializeAndWrite(reportsFile, observableReports);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                });

        // Read from files
        readAndPopulate();
    }

    public void newProduct(String... arguments) throws ParseException

    {
        if (observableProducts.stream().noneMatch(product -> product
                                                          .getName()
                                                          .equals(arguments[0])
                                                 )) {
            // Parse date and time
            SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
            arguments[3] = "" + format.parse(arguments[3]).getTime();

            // Add to observable list
            observableProducts.add(
                    new ProductEntry.Builder()
                            .name(arguments[0])
                            .cost(arguments[1])
                            .provider(arguments[2])
                            .expireDate(arguments[3])
                            .amountInStock(arguments[4]).build()
                                  );
        }
    }

    public void readAndPopulate() throws IOException
    {
        // Reader shenanigans
        BufferedReader fileReader;
        String bufferedLine;
        String[] arguments;

        // Read from customer entries file
        fileReader = new BufferedReader(new FileReader(customersFile));
        while ((bufferedLine = fileReader.readLine()) != null) {
            arguments = bufferedLine.split(",");
        }
        fileReader.close();

        // Read from product entries file
        fileReader = new BufferedReader(new FileReader(productsFile));
        while ((bufferedLine = fileReader.readLine()) != null) {
            arguments = bufferedLine.split(",");
            observableProducts.add(
                    new ProductEntry.Builder()
                            .name(arguments[0])
                            .cost(arguments[1])
                            .provider(arguments[2])
                            .expireDate(arguments[3])
                            .amountInStock(arguments[4]).build()
                                  );
        }
        fileReader.close();

        // Read from report entries file
        fileReader = new BufferedReader(new FileReader(reportsFile));
        while ((bufferedLine = fileReader.readLine()) != null) {
            arguments = bufferedLine.split(",");
        }
        fileReader.close();
    }

    public void serializeAndWrite(
            File filePointer, List<? extends AbstractTableEntry> bufferedList
                                 ) throws IOException
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
