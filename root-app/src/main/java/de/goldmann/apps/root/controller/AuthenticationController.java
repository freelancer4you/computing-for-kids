package de.goldmann.apps.root.controller;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.goldmann.apps.root.UIConstants;

@RestController
@RequestMapping(UIConstants.USER_PATH)
public class AuthenticationController {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(final Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<String>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Principal user(final Principal user) {
        return user;
    }
}
