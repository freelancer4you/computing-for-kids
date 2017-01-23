package de.goldmann.apps.root.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import de.goldmann.apps.root.dto.GoogleAccountDTO;

@Entity
@DiscriminatorValue("GOOGLEACCOUNT")
public class GoogleAccount extends UserId {

    private static final long serialVersionUID = -247943674703330516L;

    private static final int  MAXLEN_NAME      = 81;

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
        setRegistrationTyp(RegistrationTyp.GOOGLE);
    }

    public GoogleAccount(final GoogleAccountDTO acc) {
        this(acc, UserRole.USER);
    }

    public GoogleAccount(final GoogleAccountDTO acc, final UserRole role) {
        super(acc.getEmail(), role);
        setRegistrationTyp(RegistrationTyp.GOOGLE);
        this.displayName = acc.getDisplayName();
        this.familyName = acc.getFamilyName();
        this.gender = acc.getGender();
        this.givenName = acc.getGivenName();
        this.imageUrl = acc.getImageUrl();
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
                + (email != null ? "email=" + email + ", " : "")
                + (imageUrl != null ? "imageUrl=" + imageUrl : "")
                + "]";
    }

}
