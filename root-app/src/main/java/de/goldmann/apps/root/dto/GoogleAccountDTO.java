package de.goldmann.apps.root.dto;

import java.io.Serializable;

public class GoogleAccountDTO implements Serializable {

    private static final long serialVersionUID = -3682829511872578712L;

    private String            familyName;
    private String            gender;
    private String            givenName;
    private String            language;
    private String            displayName;
    private String            email;
    private String            imageUrl;

    public GoogleAccountDTO() {}

    public GoogleAccountDTO(final String familyName, final String gender, final String givenName, final String language,
            final String displayName, final String email, final String imageUrl) {
        super();
        this.familyName = familyName;
        this.gender = gender;
        this.givenName = givenName;
        this.language = language;
        this.displayName = displayName;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGender() {
        return gender;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getLanguage() {
        return language;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "GoogleAccount ["
                + (familyName != null ? "familyName=" + familyName + ", " : "")
                + (gender != null ? "gender=" + gender + ", " : "")
                + (givenName != null ? "givenName=" + givenName + ", " : "")
                + (language != null ? "language=" + language + ", " : "")
                + (displayName != null ? "displayName=" + displayName + ", " : "")
                + (email != null ? "email=" + email + ", " : "")
                + (imageUrl != null ? "imageUrl=" + imageUrl : "")
                + "]";
    }

}
