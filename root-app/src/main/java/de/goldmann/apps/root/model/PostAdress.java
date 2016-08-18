package de.goldmann.apps.root.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PostAdress implements Serializable {

    private static final long serialVersionUID = 3071306697887822761L;

    public static final int MAXLEN_STREET = 82;
    public static final int MAXLEN_POSTCODE = 10;
    public static final int MAXLEN_CITY = 40;
    private static final int  MAXLEN_HOUSE_NR  = 4;

    @Column(name = "street", nullable = false, length = MAXLEN_STREET)
    private String street;

    @Column(name = "zipcode", nullable = false, length = MAXLEN_POSTCODE)
    private String            zipcode;

    @Column(name = "city", nullable = false, length = MAXLEN_CITY)
    private String city;

    @Column(name = "housenr", nullable = false, length = MAXLEN_HOUSE_NR)
    private String            houseNr;

    // JPA-Konstruktor
    PostAdress() {
        super();
    }

    public PostAdress(final String street, final String zipcode, final String city, final String houseNr) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (city == null ? 0 : city.hashCode());
        result = prime * result + (houseNr == null ? 0 : houseNr.hashCode());
        result = prime * result + (street == null ? 0 : street.hashCode());
        result = prime * result + (zipcode == null ? 0 : zipcode.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PostAdress other = (PostAdress) obj;
        if (city == null) {
            if (other.city != null) {
                return false;
            }
        }
        else if (!city.equals(other.city)) {
            return false;
        }
        if (houseNr == null) {
            if (other.houseNr != null) {
                return false;
            }
        }
        else if (!houseNr.equals(other.houseNr)) {
            return false;
        }
        if (street == null) {
            if (other.street != null) {
                return false;
            }
        }
        else if (!street.equals(other.street)) {
            return false;
        }
        if (zipcode == null) {
            if (other.zipcode != null) {
                return false;
            }
        }
        else if (!zipcode.equals(other.zipcode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PostAdress [" + (street != null ? "street=" + street + ", " : "")
                + (zipcode != null ? "zipcode=" + zipcode + ", " : "") + (city != null ? "city=" + city + ", " : "")
                + (houseNr != null ? "houseNr=" + houseNr : "") + "]";
    }


}
