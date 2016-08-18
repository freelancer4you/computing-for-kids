package de.goldmann.map.controller;

import static de.goldmann.map.UIConstants.DATE_FORMAT;
import static de.goldmann.map.UIConstants.LIST_USERS_REQUEST_PATH;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.UserDTO;
import de.goldmann.apps.root.model.PostAdress;
import de.goldmann.apps.root.model.User;
import de.goldmann.apps.root.model.UserRole;

@Controller
public class AdminAreaController {

	private final UserRepository	userRepository;
	private final SimpleDateFormat	formatter	= new SimpleDateFormat(DATE_FORMAT);

	@Autowired
	public AdminAreaController(final UserRepository userRepository) {
		this.userRepository = Objects.requireNonNull(userRepository, "userRepository");
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = LIST_USERS_REQUEST_PATH, method = RequestMethod.GET)
	public List<UserDTO> listUsers() {

		final List<UserDTO> users = new ArrayList<>();

		for (final User user : userRepository.findAll()) {
			if (UserRole.USER.equals(user.getRole())) {
				final PostAdress adresse = user.getAdresse();
				final String registration = formatter.format(user.getRegistrationDate());
				users.add(
						new UserDTO(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(),
								user.getPasswordDigest(), user.getPhoneNumber(),
								new Adress(adresse.getStreet(), adresse.getZipcode(), adresse.getCity()),
								registration));
			}
		}

		return users;
	}
}
