package org.example;

public class Customer {
    private String name;
    private String lastName;
    private String address;
    private String phoneNumber;

    public Customer(String name, String lastName, String address, String phoneNumber){
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //getters
    public String getName(){
        return name;
    }

    public String getLastName(){
        return lastName;
    }

    public String getAddress(){return address;}

    public String getPhoneNumber(){return phoneNumber;}

    @Override
    public String toString(){
        return "User { " +
                "\n" + "Name: " + name +
                "\n" + "Last Name: " + lastName +
                "\n" + "Address: " + address +
                "\n" + "Phone Number: " + phoneNumber +
                "\n" + "}";
    }
}

