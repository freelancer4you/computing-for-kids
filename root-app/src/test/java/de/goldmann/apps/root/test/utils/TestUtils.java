
package de.goldmann.apps.root.test.utils;

import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.UserDTO;

public final class TestUtils {

    TestUtils() {
    }

    public static UserDTO buildUserDto() {
        return new UserDTO("Herr", null, "peter", "mueller", "username", "goldi23@freenet.de", "Password3",
                "phoneNumber", new Adress("neu straße", "14003", "leipzig", "39"), "2016-08-15 15:20", "Ali", "10");
    }

    public static UserDTO buildUserDto(final String salutation, final String title, final String firstName,
            final String lastName, final String userName,
            final String email, final String password, final String phoneNumber) {
        return new UserDTO(salutation, title, firstName, lastName, userName, email, password, phoneNumber,
                new Adress("street", "postcode", "city", "hr"), "2016-08-15 15:20", "Ali", "10");
    }

}


