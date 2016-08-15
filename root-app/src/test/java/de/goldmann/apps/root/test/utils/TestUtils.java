package de.goldmann.apps.root.test.utils;

import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.UserDTO;

public final class TestUtils {

    TestUtils() {
    }

    public static UserDTO buildUserDto() {
        return new UserDTO("firstName", "lastName", "userName", "test@gmx.de", "Password3", "phoneNumber",
                new Adress("street", "postcode", "city"), "2016-08-15 15:20");
    }

    public static UserDTO buildUserDto(final String firstName, final String lastName, final String userName,
            final String email, final String password, final String phoneNumber) {
        return new UserDTO(firstName, lastName, userName, email, password, phoneNumber,
                new Adress("street", "postcode", "city"), "2016-08-15 15:20");
    }
}
