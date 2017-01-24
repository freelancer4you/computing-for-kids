package de.goldmann.apps.root.controller;

import static de.goldmann.apps.root.test.utils.TestUtils.buildUserDto;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.http.auth.BasicUserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.apps.root.config.InfrastructureConfig;
import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.DefaultAccountDTO;
import de.goldmann.apps.root.model.UserId;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { InfrastructureConfig.class, AuthenticationController.class, TestConfig.class,
        AdditionalConfig.class })
@WebAppConfiguration
public class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    WebApplicationContext ctx;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final DefaultAccountDTO userDto = buildUserDto();
        final String content = mapper.writeValueAsString(userDto);
        System.out.println(content);
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(content)
                .accept(MediaType.APPLICATION_JSON).principal(new BasicUserPrincipal("test123")))
        .andDo(print()).andExpect(status().isOk());

        final String email = "test@gmx.de";
        final UserId user = userRepository.findByEmail(email);
        assertNotNull("user sollte nicht null sein", user);

        assertTrue("email not correct: " + user.getEmail(), email.equals(user.getEmail()));
    }


}
