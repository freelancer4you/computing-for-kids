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
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.apps.root.config.InfrastructureConfig;
import de.goldmann.apps.root.controller.AuthenticationControllerTest.AuthenticationConfig;
import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.UserDTO;
import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.UserId;
import de.goldmann.apps.root.services.UserActivityReport;
import de.goldmann.apps.root.services.UserServiceTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { InfrastructureConfig.class, AuthenticationController.class, TestConfig.class,
        AuthenticationConfig.class })
@WebAppConfiguration
public class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    WebApplicationContext ctx;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final UserDTO userDto = buildUserDto();
        final String content = mapper.writeValueAsString(userDto);
        System.out.println(content);
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(content)
                .accept(MediaType.APPLICATION_JSON).principal(new BasicUserPrincipal(UserServiceTest.USERNAME)))
        .andDo(print()).andExpect(status().isOk());

        final String email = "test@gmx.de";
        final UserId user = userRepository.findByEmail(email);
        assertNotNull("user sollte nicht null sein", user);

        assertTrue("email not correct: " + user.getEmail(), email.equals(user.getEmail()));
    }

    // @Configuration
    static class AuthenticationConfig {

        @Bean
        JavaMailSenderImpl mailSender() {
            return new JavaMailSenderImpl();
        }

        @Bean
        SimpleMailMessage mailMessage() {
            return new SimpleMailMessage();
        }

        @Bean
        UserActivityReport userActivityReport() {
            return new UserActivityReport() {


                @Override
                public void logout(final UserId user) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void login(final UserId user) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void registered(final UserId user, final Course course) {
                    // TODO Auto-generated method stub

                }
            };
        }
    }
}
