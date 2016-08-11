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

    @Column(name = "street", nullable = false, length = MAXLEN_STREET)
    private String street;

    @Column(name = "postleitzahl", nullable = false, length = MAXLEN_POSTCODE)
    private String postcode;

    @Column(name = "city", nullable = false, length = MAXLEN_CITY)
    private String city;

    // JPA-Konstruktor
    PostAdress() {
        super();
    }

    public PostAdress(final String street, final String postcode, final String city) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (city == null ? 0 : city.hashCode());
        result = prime * result + (postcode == null ? 0 : postcode.hashCode());
        result = prime * result + (street == null ? 0 : street.hashCode());
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
        if (postcode == null) {
            if (other.postcode != null) {
                return false;
            }
        }
        else if (!postcode.equals(other.postcode)) {
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
        return true;
    }

    @Override
    public String toString() {
        return "PostAdress [street=" + street + ", postcode=" + postcode + ", city=" + city + "]";
    }

}
