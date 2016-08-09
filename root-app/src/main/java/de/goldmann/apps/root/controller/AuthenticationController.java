package de.goldmann.apps.root.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import de.goldmann.apps.root.dto.NewUserDTO;
import de.goldmann.apps.root.services.UserService;

@Controller
@RequestMapping("/user")
public class AuthenticationController
{
    private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class);
    private final UserService   userService;

    @Autowired
    public AuthenticationController(UserService userService)
    {
        this.userService = Objects.requireNonNull(userService,
                "Parameter 'userService' darf nicht null sein.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc)
    {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<String>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public void registerUser(@RequestBody String payload)
    {
        final ObjectMapper mapper = new ObjectMapper();

        try
        {
            final NewUserDTO user = mapper.readValue(payload, NewUserDTO.class);
            userService.createUser(user);
        }
        catch (JsonParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Principal user(Principal user)
    {
        return user;
    }
}
