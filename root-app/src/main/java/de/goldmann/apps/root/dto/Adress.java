package de.goldmann.apps.root.dto;

public class Adress {
    private String street;
    private String postcode;
    private String city;

    Adress() {
        super();
    }

    public Adress(final String street, final String postcode, final String city) {
        super();
        this.street = street;
        this.postcode = postcode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }
}
