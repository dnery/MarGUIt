package br.usp.icmc.ssc0103.model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

// Customer table entry model (Builder pattern can be seen here)
public class CustomerEntry implements AbstractTableEntry, Serializable
{
    private final SimpleStringProperty name;
    private final SimpleStringProperty address;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty userEmail;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty password;

    public CustomerEntry(Builder builder)
    {
        name = builder.nestedName;
        address = builder.nestedAddress;
        phoneNumber = builder.nestedPhoneNumber;
        userEmail = builder.nestedUserEmail;
        userName = builder.nestedUserName;
        password = builder.nestedPassword;
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty()
    {
        return name;
    }

    public String getAddress()
    {
        return address.get();
    }

    public void setAddress(String address)
    {
        this.address.set(address);
    }

    public SimpleStringProperty addressProperty()
    {
        return address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber.set(phoneNumber);
    }

    public SimpleStringProperty phoneNumberProperty()
    {
        return phoneNumber;
    }

    public String getUserEmail()
    {
        return userEmail.get();
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail.set(userEmail);
    }

    public SimpleStringProperty userEmailProperty()
    {
        return userEmail;
    }

    public String getUserName()
    {
        return userName.get();
    }

    public void setUserName(String userName)
    {
        this.userName.set(userName);
    }

    public SimpleStringProperty userNameProperty()
    {
        return userName;
    }

    public String getPassword()
    {
        return password.get();
    }

    public void setPassword(String password)
    {
        this.password.set(password);
    }

    public SimpleStringProperty passwordProperty()
    {
        return password;
    }

    @Override
    public String serialize()
    {
        return name.get() +
               "," + address.get() +
               "," + phoneNumber.get() +
               "," + userEmail.get() +
               "," + userName.get() +
               "," + password.get();
    }

    // Not too readable builder
    public static class Builder
    {
        private final SimpleStringProperty nestedName;
        private final SimpleStringProperty nestedAddress;
        private final SimpleStringProperty nestedPhoneNumber;
        private final SimpleStringProperty nestedUserEmail;
        private final SimpleStringProperty nestedUserName;
        private final SimpleStringProperty nestedPassword;

        public Builder()
        {
            nestedName = new SimpleStringProperty();
            nestedAddress = new SimpleStringProperty();
            nestedPhoneNumber = new SimpleStringProperty();
            nestedUserEmail = new SimpleStringProperty();
            nestedUserName = new SimpleStringProperty();
            nestedPassword = new SimpleStringProperty();
        }

        public Builder name(String name)
        {
            nestedName.set(name);
            return this;
        }

        public Builder address(String address)
        {
            nestedName.set(address);
            return this;
        }

        public Builder phoneNumber(String phoneNumber)
        {
            nestedName.set(phoneNumber);
            return this;
        }

        public Builder userEmail(String userEmail)
        {
            nestedName.set(userEmail);
            return this;
        }

        public Builder userName(String userName)
        {
            nestedName.set(userName);
            return this;
        }

        public Builder password(String password)
        {
            nestedName.set(password);
            return this;
        }

        public CustomerEntry build()
        {
            return new CustomerEntry(this);
        }
    }
}
