package de.goldmann.apps.root.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import de.goldmann.apps.root.dto.GoogleAccountDTO;

@Entity
@DiscriminatorValue("GOOGLEACCOUNT")
public class GoogleAccount extends UserId {

    private static final long serialVersionUID = -247943674703330516L;

    @Column(name = "familyname", nullable = false, length = MAXLEN_NAME)
    private String            familyName;

    @Column(name = "givenname", nullable = true, length = MAXLEN_NAME)
    private String            givenName;

    @Column(name = "gender", nullable = false)
    private String            gender;

    @Column(name = "language", nullable = false)
    private String            language;

    @Column(name = "displayName", nullable = false)
    private String            displayName;

    @Column(name = "imageUrl", nullable = false)
    private String            imageUrl;

    GoogleAccount() {
    }

    public GoogleAccount(final GoogleAccountDTO acc) {
        this(acc, UserRole.USER);
    }

    public GoogleAccount(final GoogleAccountDTO acc, final UserRole role) {
        super(acc.getEmail(), role, RegistrationTyp.GOOGLEACCOUNT);
        displayName = acc.getDisplayName();
        familyName = acc.getFamilyName();
        gender = acc.getGender();
        givenName = acc.getGivenName();
        imageUrl = acc.getImageUrl();
        language = acc.getLanguage();
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
                + (getEmail() != null ? "email=" + getEmail() + ", " : "")
                + (imageUrl != null ? "imageUrl=" + imageUrl : "")
                + "]";
    }

}
