package de.goldmann.apps.root.test.utils;

import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.NewUserDTO;

public final class TestUtils {

    TestUtils() {
    }

    public static NewUserDTO buildUserDto() {
        return new NewUserDTO("firstName", "lastName", "userName", "test@gmx.de", "Password3", "phoneNumber",
                new Adress("street", "postcode", "city"));
    }

    public static NewUserDTO buildUserDto(final String firstName, final String lastName, final String userName,
            final String email, final String password, final String phoneNumber) {
        return new NewUserDTO(firstName, lastName, userName, email, password, phoneNumber,
                new Adress("street", "postcode", "city"));
    }
}
