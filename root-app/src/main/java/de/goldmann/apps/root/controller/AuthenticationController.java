package de.goldmann.apps.root.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.apps.root.dto.UserDTO;
import de.goldmann.apps.root.model.User;
import de.goldmann.apps.root.services.UserActivityReport;
import de.goldmann.apps.root.services.UserService;

@Controller
@RequestMapping("/user")
public class AuthenticationController {
    private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class);
    private final UserService userService;
    private final UserActivityReport activityReport;

    @Autowired
    public AuthenticationController(final UserService userService, @Lazy final UserActivityReport activityReport) {
        this.userService = Objects.requireNonNull(userService, "Parameter 'userService' darf nicht null sein.");

        this.activityReport = activityReport;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(final Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<String>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public void registerUser(@RequestBody final String payload) {
        final ObjectMapper mapper = new ObjectMapper();

        try {
            final UserDTO user = mapper.readValue(payload, UserDTO.class);
            final User storedUser = userService.createUser(user);

            if (activityReport != null) {
                this.activityReport.registered(storedUser);
            }

        }
        catch (final JsonParseException e) {
            // TODO unzureichende Fehlerbehandlung
            LOGGER.error("Fehler bei der Benutzerregistrierung:", e);
        }
        catch (final JsonMappingException e) {
            // TODO unzureichende Fehlerbehandlung
            LOGGER.error("Fehler bei der Benutzerregistrierung:", e);
        }
        catch (final IOException e) {
            // TODO unzureichende Fehlerbehandlung
            LOGGER.error("Fehler bei der Benutzerregistrierung:", e);
        }

    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Principal user(final Principal user) {
        return user;
    }
}
