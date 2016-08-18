package de.goldmann.apps.root.dto;

public class Adress {
    private String street;
    private String zipcode;
    private String city;
    private String houseNr;

    Adress() {
        super();
    }

    public Adress(final String street, final String zipcode, final String city, final String houseNr) {
        super();
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.houseNr = houseNr;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getHouseNr() {
        return houseNr;
    }

}
