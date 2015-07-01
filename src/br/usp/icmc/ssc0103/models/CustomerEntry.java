package br.usp.icmc.ssc0103.models;

public class CustomerEntry implements AbstractTableEntry
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
}
